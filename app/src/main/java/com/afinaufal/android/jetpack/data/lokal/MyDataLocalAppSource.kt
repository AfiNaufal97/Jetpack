package com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal

import androidx.lifecycle.LiveData
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.myroom.MyAppDao
import androidx.paging.DataSource

class MyDataLocalAppSource(private val dao: MyAppDao){

    companion object{
        private var INSTANCE:MyDataLocalAppSource? = null

        fun getInstance(dao: MyAppDao):MyDataLocalAppSource =
            INSTANCE?: MyDataLocalAppSource(dao)
    }

    fun getAllMyMovie():DataSource.Factory<Int, MyMovieEntity> = dao.getMyMovie()
    fun getAllMyTv():DataSource.Factory<Int, MyTvEntity> = dao.getMyTv()
    fun getDetailMyMovie(id:Int):LiveData<MyMovieEntity> = dao.getMyMovieById(id)
    fun getDetailMyTv(id:Int):LiveData<MyTvEntity> = dao.getMyTvById(id)
    fun insertMyMovie(movie:List<MyMovieEntity>) = dao.insertMyMovie(movie)
    fun insertMyTv(tv:List<MyTvEntity>) = dao.insertMyTv(tv)
    fun updateMyMovie(movie:MyMovieEntity, id:Int) = dao.updateMyMovie(movie)
    fun updateMyTv(tv:MyTvEntity, id:Int) = dao.updateMyTv(tv)
    fun setFavoritMovie(movie:MyMovieEntity, value:Boolean){
        movie.favorite = value
        dao.updateMyMovie(movie)
    }

    fun setFavoriteTv(tv:MyTvEntity, value:Boolean){
        tv.favorit = value
        dao.updateMyTv(tv)
    }

    fun getFavoritMyMovie():DataSource.Factory<Int, MyMovieEntity> = dao.getFavoriteMyMovie()
    fun getFavoritMyTv():DataSource.Factory<Int, MyTvEntity> = dao.getFavoriteMyTv()
}