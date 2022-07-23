package com.afinaufal.androiddasar.afinaufalsubmission_1.data.remotedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afinaufal.androiddasar.afinaufalsubmission_1.api.MyApiResponse
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.JsonRead

class RemoteMyApps private constructor(private val myJsonHelper:JsonRead){

    companion object {

        @Volatile
        private var instanceMyData: RemoteMyApps? = null

        fun getInstance(myHelper: JsonRead): RemoteMyApps = instanceMyData ?: synchronized(this) {
            instanceMyData?:RemoteMyApps(myHelper).apply {
                instanceMyData = this
            }
        }
    }

    fun getMovie():LiveData<MyApiResponse<List<MyMovieEntity>>>{
       val resultMovie = MutableLiveData<MyApiResponse<List<MyMovieEntity>>>()
        resultMovie.value = MyApiResponse.sucess(myJsonHelper.loadMyMovie())
        return resultMovie
    }

    interface LoadMyMovie{
        fun onAllMovieReceived(myMovieResponse: List<MyMovieEntity>)
    }

    fun getTv():LiveData<MyApiResponse<List<MyTvEntity>>>{
       val resultTv = MutableLiveData<MyApiResponse<List<MyTvEntity>>>()
        resultTv.value = MyApiResponse.sucess(myJsonHelper.loadMyTv())
        return resultTv
    }

    interface LoadMyTv{
        fun onAllMTvReceived(myTvResponse: List<MyTvEntity>)
    }


    fun getMyMovieDetail(id:Int):LiveData<MyApiResponse<MyMovieEntity>>{
        val myMovieDetail = MutableLiveData<MyApiResponse<MyMovieEntity>>()
        myMovieDetail.value = MyApiResponse.sucess(myJsonHelper.loadDetailMyMovie(id))
        return myMovieDetail
    }

    interface loadMyDetailMovie{
        fun onDetailMyMovie(myDetailMovieResponse : MyMovieEntity)
    }


    fun getMyTvDetail(id:Int):LiveData<MyApiResponse<MyTvEntity>>{
        val myTvDetail = MutableLiveData<MyApiResponse<MyTvEntity>>()
        myTvDetail.value = MyApiResponse.sucess(myJsonHelper.loadDetailMyTv(id))
        return myTvDetail
    }

    interface loadMyDetailTv{
        fun onDetailMyTv(myDetailTvesponse : MyTvEntity)
    }
}
