package com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource

import com.afinaufal.androiddasar.afinaufalsubmission_1.dataenum.Status

data class Resource<T>(val status: Status, val data:T?, val message:String?){
    companion object{
        fun<T> sucess(data:T?):Resource<T> = Resource(Status.SUCCESS, data, null)
        fun<T> error(message:String?, data:T?):Resource<T> = Resource(Status.ERROR, data, message)
        fun<T> loading(data:T?):Resource<T> = Resource(Status.LOADING, data, null)
    }
}