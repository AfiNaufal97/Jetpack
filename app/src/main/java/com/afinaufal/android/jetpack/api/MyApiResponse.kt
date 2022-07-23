package com.afinaufal.androiddasar.afinaufalsubmission_1.api

import com.afinaufal.androiddasar.afinaufalsubmission_1.dataenum.StatusFromResponse

class MyApiResponse<T>(val stausResponse:StatusFromResponse, val body:T, val message:String?){
    companion object{
        fun<T> sucess(body:T):MyApiResponse<T> = MyApiResponse(StatusFromResponse.SUCCESS, body, null)
        fun<T> empty(message: String, body:T):MyApiResponse<T> = MyApiResponse(StatusFromResponse.EMPTY, body, message)
        fun<T> error(message: String, body:T):MyApiResponse<T> = MyApiResponse(StatusFromResponse.ERROR, body, message)
    }
}