package com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.myroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity

@Database(
    entities = [MyMovieEntity::class, MyTvEntity::class],
    version = 1,
)

abstract class MyRoomDB:RoomDatabase(){

    abstract fun myAppEntertaimentDao():MyAppDao

    companion object{
        @Volatile
        private var INSTANCE: MyRoomDB? = null

        fun getInstance(context: Context):MyRoomDB =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDB::class.java,
                    "MyRoomDB.db"
                ).build().also {
                    INSTANCE = it
                }
            }
    }
}