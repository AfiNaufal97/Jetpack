package com.afinaufal.androiddasar.afinaufalsubmission_1.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.afinaufal.androiddasar.afinaufalsubmission_1.api.MyApiResponse
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource
import com.afinaufal.androiddasar.afinaufalsubmission_1.dataenum.StatusFromResponse


@Suppress("LeakingThis")
abstract class MyNetworkBoundResource<ResultType, RequestType>(private val myExecutor: AppExecutor) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        val myDBSource = loadMyDB()
        result.addSource(myDBSource){
            result.removeSource(myDBSource)
            if(myFetchFetchData(it)){
                myFetchFromNetwork(myDBSource)
            }else{
                result.addSource(myDBSource){
                    result.value = Resource.sucess(it)
                }
            }
        }


    }

    private fun myFetchFromNetwork(myDBSource: LiveData<ResultType>){
        val apiResponse = createCall()

        result.addSource(myDBSource){
            result.value = Resource.loading(it)
        }

        result.addSource(apiResponse){ responseApi ->
            result.removeSource(apiResponse)
            result.removeSource(myDBSource)
            when(responseApi.stausResponse){
                StatusFromResponse.SUCCESS ->
                    myExecutor.dataIO().execute{
                        saveCallResult(responseApi.body)
                        myExecutor.mainThread().execute{
                            result.addSource(loadMyDB()){ data ->
                                result.value = Resource.sucess(data)
                            }
                        }
                    }
                StatusFromResponse.EMPTY -> myExecutor.mainThread().execute {
                    result.addSource(loadMyDB()){ data ->
                        result.value = Resource.sucess(data)
                    }
                }

                StatusFromResponse.ERROR -> {
                    onFetchisFailed()
                    result.addSource(myDBSource){ data ->
                        result.value = Resource.error(responseApi.message, data)
                    }
                }
            }
        }
    }

    protected fun onFetchisFailed(){}

    abstract fun saveCallResult(body:RequestType)

    abstract fun createCall(): LiveData<MyApiResponse<RequestType>>

    abstract fun myFetchFetchData(it: ResultType?): Boolean

    abstract fun loadMyDB(): LiveData<ResultType>

    fun myLiveData():LiveData<Resource<ResultType>> = result

}