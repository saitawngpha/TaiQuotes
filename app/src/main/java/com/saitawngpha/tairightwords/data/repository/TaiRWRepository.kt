package com.saitawngpha.tairightwords.data.repository

import android.content.Context
import com.saitawngpha.tairightwords.model.TaiRWModel
import com.saitawngpha.tairightwords.utils.Helper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

@ViewModelScoped
class TaiRWRepository @Inject constructor(
    private val context: Context
) {

    fun getDataFromJson(): Flow<TaiRWModel> = flow {
        try {
            val taiRWModel = Helper.getJsonData(context)
            emit(taiRWModel)
        } catch (e: Exception) {
            println("Repository: error ${e.localizedMessage}")
        }
    }

    fun loadProverbData(searchText: String): Flow<List<TaiRWModel.AllProverb.Proverb>> = flow {
        try {
            println("### searchText: $searchText")
            val taiRWModel = Helper.getJsonData(context)
            val filteredProverbs = taiRWModel.allProverbs
                .find { it.key == searchText }
                ?.proverbs ?: emptyList()

            emit(filteredProverbs)
        } catch (e: Exception) {
            // Handle exception
            println("Repository: error loadProverbData ${e.localizedMessage}")
            throw e
        }
    }

    fun getRandomProverb(): Flow<String?> = flow {
        try {
            val taiRWModel = Helper.getJsonData(context)

            // Flatten the list of proverbs into a single list
            val allProverbs = taiRWModel.allProverbs.flatMap { it.proverbs }

            // Check if the list is not empty before getting a random proverb
            if (allProverbs.isNotEmpty()) {
                val randomProverb = allProverbs.random().proverb
                emit(randomProverb)
            } else {
                emit(null) // Return null if there are no proverbs
            }
        } catch (e: Exception) {
            // Handle exception
            println("Repository: error getRandomProverb ${e.localizedMessage}")
            throw e
        }
    }

}