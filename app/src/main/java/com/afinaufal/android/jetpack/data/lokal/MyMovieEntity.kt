package com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "mymovie")
data class MyMovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id:Int,

        @ColumnInfo(name = "nameMovie")
        val nameMovie:String,

        @ColumnInfo(name = "year")
        val year:String,

        @ColumnInfo(name = "imaage")
        val imaage:String,

        @ColumnInfo(name = "raiting")
        val raiting:String,

        @ColumnInfo(name = "sinopsis")
        val sinopsis:String,

        @ColumnInfo(name = "duration")
        val duration:String,

        @ColumnInfo(name = "genre")
        val genre:String,

        @ColumnInfo(name = "trailer")
        val trailer:String,

        @ColumnInfo(name = "link")
        val link:String,

        @ColumnInfo(name = "favorite")
        var favorite:Boolean = false
    ):Parcelable