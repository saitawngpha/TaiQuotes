package com.saitawngpha.tairightwords.utils

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

sealed class RequestState<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T?) : RequestState<T>(data = data)
    class Error<T>(message: String, data: T? = null): RequestState<T>(data, message)
    class Loading<T>(data: T? = null) : RequestState<T>(data)
}