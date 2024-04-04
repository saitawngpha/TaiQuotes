package com.saitawngpha.tairightwords.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saitawngpha.tairightwords.data.repository.TaiRWRepository
import com.saitawngpha.tairightwords.model.ResponseData
import com.saitawngpha.tairightwords.model.TaiRWModel
import com.saitawngpha.tairightwords.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

@OptIn(FlowPreview::class)
@HiltViewModel
class RightWordViewModel @Inject constructor(
    private val taiRWRepository: TaiRWRepository
): ViewModel() {

    private val _jsonProverbs = MutableStateFlow<List<TaiRWModel.AllProverb.Proverb>>(emptyList())
    var jsonProverbs : StateFlow<List<TaiRWModel.AllProverb.Proverb>> = _jsonProverbs

    private val _jsonDataKey = MutableStateFlow<List<String>>(emptyList())
    var jsonDataKey : StateFlow<List<String>> = _jsonDataKey

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _randomText = MutableStateFlow<String>("")
    val randomText = _randomText.asStateFlow()

    private var isRandomProverbLoaded = false

    private val _nameValue = MutableStateFlow<ResponseData?>(null)
    val nameValue: StateFlow<ResponseData?>
        get() = _nameValue

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading


    private val _jsonSearchList = MutableStateFlow(listOf<TaiRWModel.AllProverb.Proverb>())
    val jsonSearchList = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_jsonSearchList) { text, proverbs ->
            if (text.isEmpty()) {
                proverbs
            }else{
                proverbs.filter {
                    it.doesMatchSearch(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _jsonSearchList.value
        )

   init {
       loadJsonFromAssetsKey()
   }

    fun loadProverbs(searchText: String) {
        viewModelScope.launch {
            try {
                val proverbs = taiRWRepository.loadProverbData(searchText).first()
                _jsonSearchList.value = proverbs
                //_jsonProverbs.value = proverbs
            } catch (e: Exception) {
                // Handle exception
                println("ViewModel: error ${e.localizedMessage}")
            }
        }
    }

    private fun loadJsonFromAssetsKey() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                taiRWRepository.getDataFromJson().collect{ taiData ->
                    //println("####, $taiData")
                    _jsonDataKey.value = taiData.allProverbs.map { it.key }
                }
            }
        } catch (e: Exception){
            println("### Error: ${e.localizedMessage}")
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun clearSearchText() {
        if (_searchText.value.isNotEmpty()) {
            _searchText.value = ""
        }
    }

    fun loadRandomProverb() {
        if (!isRandomProverbLoaded) {
            viewModelScope.launch {
                try {
                    taiRWRepository.getRandomProverb().collect { randomProverb ->
                        _randomText.value = randomProverb.toString()
                        isRandomProverbLoaded = true
                    }
                } catch (e: Exception) {
                    // Handle exception
                    println("ViewModel: error ${e.localizedMessage}")
                }
            }
        }
    }

}