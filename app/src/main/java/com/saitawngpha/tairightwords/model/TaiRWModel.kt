package com.saitawngpha.tairightwords.model


import android.content.Context
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.Gson
import com.saitawngpha.tairightwords.R
import java.io.BufferedReader
import java.io.InputStreamReader

@Keep
data class TaiRWModel(
    @SerializedName("allProverbs")
    val allProverbs: List<AllProverb>
) {
    @Keep
    data class AllProverb(
        @SerializedName("key")
        val key: String,
        @SerializedName("proverbs")
        val proverbs: List<Proverb>
    ) {
        @Keep
        data class Proverb(
            @SerializedName("proverb")
            val proverb: String
        ) {
            fun doesMatchSearch(queryText: String): Boolean {
                val matchingCombination = listOf(
                    "$proverb",
                    " $proverb",
                    "$proverb ",
                    "${proverb.first()}"
                )
                return matchingCombination.any {
                    it.contains(queryText, ignoreCase = true)
                }
            }
        }
    }
}
