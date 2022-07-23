package com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "mytv")
data class MyTvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "nameShow")
    val nameShow:String,

    @ColumnInfo(name = "images")
    val images:String,

    @ColumnInfo(name = "raiting")
    val raiting:String,

    @ColumnInfo(name = "genre")
    val genre:String,

    @ColumnInfo(name = "duration")
    val duration:String,

    @ColumnInfo(name = "sinopsis")
    val sinopsis:String,

    @ColumnInfo(name = "trailer")
    val trailer:String,

    @ColumnInfo(name = "link")
    val link:String,

    @ColumnInfo(name = "favorit")
    var favorit:Boolean = false
):Parcelable