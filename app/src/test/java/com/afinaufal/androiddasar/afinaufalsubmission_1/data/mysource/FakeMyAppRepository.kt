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
import java.lang.Appendable

class FakeMyAppRepository constructor(
    private val myRemote:RemoteMyApps,
    private val localAppSource: MyDataLocalAppSource,
    private val appExe:AppExecutor
):MyAppDataSource {

    override fun getAllMovie(): LiveData<Resource<PagedList<MyMovieEntity>>> {
        return object : MyNetworkBoundResource<PagedList<MyMovieEntity>, List<MyMovieEntity>>(appExe){
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
                localAppSource.insertMyMovie(movieList)
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
                return LivePagedListBuilder(localAppSource.getAllMyMovie(), configData).build()
            }

        }.myLiveData()
    }

    override fun getAllTv(): LiveData<Resource<PagedList<MyTvEntity>>> {
        return object : MyNetworkBoundResource<PagedList<MyTvEntity>, List<MyTvEntity>>(appExe){
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
                localAppSource.insertMyTv(myTvList)
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
                return LivePagedListBuilder(localAppSource.getAllMyTv(), configMyTv).build()
            }

        }.myLiveData()
    }

    override fun getDetailMyMovie(id: Int): LiveData<Resource<MyMovieEntity>> {
        return object : MyNetworkBoundResource<MyMovieEntity, MyMovieEntity>(appExe){
            override fun saveCallResult(body: MyMovieEntity) {
                localAppSource.updateMyMovie(body, id)
            }

            override fun createCall(): LiveData<MyApiResponse<MyMovieEntity>> {
                return myRemote.getMyMovieDetail(id)
            }

            override fun myFetchFetchData(it: MyMovieEntity?): Boolean {
                return it == null
            }

            override fun loadMyDB(): LiveData<MyMovieEntity> {
                return localAppSource.getDetailMyMovie(id)
            }

        }.myLiveData()
    }

    override fun getDetailMyTv(id: Int): LiveData<Resource<MyTvEntity>> {
        return object : MyNetworkBoundResource<MyTvEntity, MyTvEntity>(appExe){
            override fun saveCallResult(body: MyTvEntity) {
                localAppSource.updateMyTv(body, id)
            }

            override fun createCall(): LiveData<MyApiResponse<MyTvEntity>> {
                return myRemote.getMyTvDetail(id)
            }

            override fun myFetchFetchData(it: MyTvEntity?): Boolean {
                return it == null
            }

            override fun loadMyDB(): LiveData<MyTvEntity> {
                return localAppSource.getDetailMyTv(id)
            }

        }.myLiveData()
    }

    override fun setFavoriteMovie(movie: MyMovieEntity, value: Boolean) {
        appExe.dataIO().execute{localAppSource.setFavoritMovie(movie, value)}
    }

    override fun setFavoriteTv(tv: MyTvEntity, value: Boolean) {
        appExe.dataIO().execute{localAppSource.setFavoriteTv(tv, value)}
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MyMovieEntity>> {
        val favorit = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localAppSource.getFavoritMyMovie(), favorit).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<MyTvEntity>> {
        val favorit = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localAppSource.getFavoritMyTv(), favorit).build()
    }
}