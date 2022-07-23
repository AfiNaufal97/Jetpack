package com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.myroom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import androidx.paging.DataSource

@Dao
interface MyAppDao {
    @Query("SELECT * FROM mymovie")
    fun getMyMovie():DataSource.Factory<Int, MyMovieEntity>

    @Query("SELECT * FROM mytv")
    fun getMyTv():DataSource.Factory<Int, MyTvEntity>

    @Query("SELECT * FROM mymovie WHERE id=:id")
    fun getMyMovieById(id:Int):LiveData<MyMovieEntity>

    @Query("SELECT * FROM mytv WHERE id=:id")
    fun getMyTvById(id:Int):LiveData<MyTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyMovie(users:List<MyMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyTv(users:List<MyTvEntity>)

    @Update
    fun updateMyMovie(users:MyMovieEntity)

    @Update
    fun updateMyTv(users:MyTvEntity)

    @Delete
    fun deleteMyMovie(users:MyMovieEntity)

    @Query("SELECT * FROM mymovie WHERE favorite=1")
    fun getFavoriteMyMovie():DataSource.Factory<Int, MyMovieEntity>

    @Query("SELECT * FROM mytv WHERE favorit=1")
    fun getFavoriteMyTv():DataSource.Factory<Int, MyTvEntity>


}