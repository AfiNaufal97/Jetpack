package com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afinaufal.androiddasar.afinaufalsubmission_1.api.MyApiResponse
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyDataLocalAppSource
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.remotedata.RemoteMyApps
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.AppExecutor
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.MyNetworkBoundResource

class MyAppRepository private constructor(
    private val myRemote:RemoteMyApps,
    private val myDataLocalAppSource: MyDataLocalAppSource,
    private val appExecutor: AppExecutor):MyAppDataSource {

    companion object{
        @Volatile
        private var myInstance:MyAppRepository? = null

        fun getInstance(remoteMyApps: RemoteMyApps, dataLocal:MyDataLocalAppSource, appExecutor: AppExecutor):MyAppRepository = myInstance?: synchronized(this){
           myInstance?:MyAppRepository(remoteMyApps, dataLocal, appExecutor).apply {
               myInstance = this
           }
        }
    }

    override fun getAllMovie(): LiveData<Resource<PagedList<MyMovieEntity>>> {
        return object : MyNetworkBoundResource<PagedList<MyMovieEntity>, List<MyMovieEntity>>(appExecutor){
            override fun saveCallResult(body: List<MyMovieEntity>) {
                val movieList = ArrayList<MyMovieEntity>()
                for(response in body){
                    val movieData = MyMovieEntity(
                        response.id,
                        response.nameMovie,
                        response.year,
                        response.imaage,
                        response.raiting,
                        response.sinopsis,
                        response.duration,
                        response.genre,
                        response.trailer,
                        response.link,
                    )
                    movieList.add(movieData)
                    Log.d("movie", movieList.toString())
                }
                myDataLocalAppSource.insertMyMovie(movieList)
            }

            override fun createCall(): LiveData<MyApiResponse<List<MyMovieEntity>>> = myRemote.getMovie()

            override fun myFetchFetchData(it: PagedList<MyMovieEntity>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun loadMyDB(): LiveData<PagedList<MyMovieEntity>> {
                val configData = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(myDataLocalAppSource.getAllMyMovie(), configData).build()
            }

        }.myLiveData()
    }

    override fun getAllTv(): LiveData<Resource<PagedList<MyTvEntity>>> {
        return object : MyNetworkBoundResource<PagedList<MyTvEntity>, List<MyTvEntity>>(appExecutor){
            override fun saveCallResult(body: List<MyTvEntity>) {
                val myTvList = ArrayList<MyTvEntity>()
                for(response in body){
                    val dataMyTv = MyTvEntity(
                        response.id,
                        response.nameShow,
                        response.images,
                        response.raiting,
                        response.genre,
                        response.duration,
                        response.sinopsis,
                        response.trailer,
                        response.link
                    )
                    myTvList.add(dataMyTv)
                    Log.d("myTv", myTvList.toString())
                }
                myDataLocalAppSource.insertMyTv(myTvList)
            }

            override fun createCall(): LiveData<MyApiResponse<List<MyTvEntity>>> =
                myRemote.getTv()

            override fun myFetchFetchData(it: PagedList<MyTvEntity>?): Boolean {
               return it == null || it.isEmpty()
            }

            override fun loadMyDB(): LiveData<PagedList<MyTvEntity>> {
                val configMyTv = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(myDataLocalAppSource.getAllMyTv(), configMyTv).build()
            }

        }.myLiveData()
    }

    override fun getDetailMyMovie(id: Int): LiveData<Resource<MyMovieEntity>> {
        return object : MyNetworkBoundResource<MyMovieEntity, MyMovieEntity>(appExecutor){
            override fun saveCallResult(body: MyMovieEntity) {
                myDataLocalAppSource.updateMyMovie(body, id)
            }

            override fun createCall(): LiveData<MyApiResponse<MyMovieEntity>> {
                return myRemote.getMyMovieDetail(id)
            }

            override fun myFetchFetchData(it: MyMovieEntity?): Boolean {
                return it == null
            }

            override fun loadMyDB(): LiveData<MyMovieEntity> {
               return myDataLocalAppSource.getDetailMyMovie(id)
            }

        }.myLiveData()
    }

    override fun getDetailMyTv(id: Int): LiveData<Resource<MyTvEntity>> {
        return object : MyNetworkBoundResource<MyTvEntity, MyTvEntity>(appExecutor){
            override fun saveCallResult(body: MyTvEntity) {
                myDataLocalAppSource.updateMyTv(body, id)
            }

            override fun createCall(): LiveData<MyApiResponse<MyTvEntity>> {
                return myRemote.getMyTvDetail(id)
            }

            override fun myFetchFetchData(it: MyTvEntity?): Boolean {
                return it == null
            }

            override fun loadMyDB(): LiveData<MyTvEntity> {
                return myDataLocalAppSource.getDetailMyTv(id)
            }

        }.myLiveData()
    }


    override fun setFavoriteMovie(movie: MyMovieEntity, value: Boolean) {
        appExecutor.dataIO().execute{myDataLocalAppSource.setFavoritMovie(movie, value)}
    }

    override fun setFavoriteTv(tv: MyTvEntity, value: Boolean) {
        appExecutor.dataIO().execute{myDataLocalAppSource.setFavoriteTv(tv, value)}
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MyMovieEntity>> {
        val favorit = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(myDataLocalAppSource.getFavoritMyMovie(), favorit).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<MyTvEntity>> {
        val favorit = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(myDataLocalAppSource.getFavoritMyTv(), favorit).build()
    }

}