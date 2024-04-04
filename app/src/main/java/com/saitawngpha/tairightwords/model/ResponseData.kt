package com.saitawngpha.tairightwords.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 12/03/2024.
 */
@Keep
data class ResponseData(
    @SerializedName("average_duration")
    val averageDuration: Double,
    @SerializedName("data")
    val `data`: List<Any>,
    @SerializedName("duration")
    val duration: Double,
    @SerializedName("is_generating")
    val isGenerating: Boolean
)
