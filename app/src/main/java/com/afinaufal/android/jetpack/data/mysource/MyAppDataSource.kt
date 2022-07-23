package com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity

interface MyAppDataSource {
    fun getAllMovie():LiveData<Resource<PagedList<MyMovieEntity>>>
    fun getAllTv():LiveData<Resource<PagedList<MyTvEntity>>>
    fun getDetailMyMovie(id:Int): LiveData<Resource<MyMovieEntity>>
    fun getDetailMyTv(id:Int): LiveData<Resource<MyTvEntity>>
    fun setFavoriteMovie(movie:MyMovieEntity, value:Boolean)
    fun setFavoriteTv(tv:MyTvEntity, value: Boolean)
    fun getFavoriteMovie():LiveData<PagedList<MyMovieEntity>>
    fun getFavoriteTv():LiveData<PagedList<MyTvEntity>>
}