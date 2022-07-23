package com.afinaufal.androiddasar.afinaufalsubmission_1.utility

import android.content.Context
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonRead(private val contextApp:Context) {

    private fun parsingMyJsonToString(jsonName:String):String?{
        return try{
            val `is` = contextApp.assets.open(jsonName)
            val bufferFile = ByteArray(`is`.available())
            `is`.read(bufferFile)
            String(bufferFile)
        }catch(exception:IOException){
            exception.printStackTrace()
            null
        }
    }

    fun loadMyMovie():List<MyMovieEntity>{
        val list = ArrayList<MyMovieEntity>()
        try{
            val myResponseObject = JSONObject(parsingMyJsonToString("movie.json").toString())
            val listArray = myResponseObject.getJSONArray("movie")
            for(i in 0 until listArray.length()){
                val movie = listArray.getJSONObject(i)
                val id = movie.getInt("id")
                val nameMovie = movie.getString("nameMovie")
                val year = movie.getString("year")
                val image = movie.getString("image")
                val raiting = movie.getString("raiting")
                val sinopsis = movie.getString("sinopsis")
                val duration = movie.getString("duration")
                val genre = movie.getString("genre")
                val trailer = movie.getString("trailer")
                val link = movie.getString("link")

                val myMovieResponse = MyMovieEntity(id, nameMovie, year, image, raiting, sinopsis, duration, genre, trailer, link)
                list.add(myMovieResponse)
            }
        }catch(exception:JSONException){
            exception.printStackTrace()
        }
        return list
    }


    fun loadDetailMyMovie(idMovie:Int):MyMovieEntity{
        lateinit var dataObjekMovie:MyMovieEntity
        try{
            val myResponseObject = JSONObject(parsingMyJsonToString("movie.json").toString())
            val listArray = myResponseObject.getJSONArray("movie")
            for(i in 0 until listArray.length()){
                val movie = listArray.getJSONObject(i)
                if(movie.getInt("id") == idMovie) {
                    val id = movie.getInt("id")
                    val nameMovie = movie.getString("nameMovie")
                    val year = movie.getString("year")
                    val image = movie.getString("image")
                    val raiting = movie.getString("raiting")
                    val sinopsis = movie.getString("sinopsis")
                    val duration = movie.getString("duration")
                    val genre = movie.getString("genre")
                    val trailer = movie.getString("trailer")
                    val link = movie.getString("link")

                    dataObjekMovie = MyMovieEntity(
                        id,
                        nameMovie,
                        year,
                        image,
                        raiting,
                        sinopsis,
                        duration,
                        genre,
                        trailer,
                        link
                    )
                }
            }
        }catch(exception:JSONException){
            exception.printStackTrace()
        }
        return dataObjekMovie
    }


    fun loadMyTv():List<MyTvEntity>{
        val listTv = ArrayList<MyTvEntity>()
        try{
            val myResponseTv = JSONObject(parsingMyJsonToString("tv.json").toString())
            val list = myResponseTv.getJSONArray("tv")
            for(i in 0 until list.length()){
                val tv = list.getJSONObject(i)
                val id = tv.getInt("id")
                val nameShow = tv.getString("nameShow")
                val image = tv.getString("image")
                val raiting = tv.getString("raiting")
                val genre = tv.getString("genre")
                val duration = tv.getString("duration")
                val sinopsis = tv.getString("sinopsis")
                val trailer = tv.getString("trailer")
                val link = tv.getString("link")

                val myTvResponse = MyTvEntity(id, nameShow, image, raiting, genre, duration, sinopsis, trailer, link)
                listTv.add(myTvResponse)
            }
        }catch(exception:JSONException){
            exception.printStackTrace()
        }
        return listTv
    }

    fun loadDetailMyTv(idMyTv:Int):MyTvEntity{
        lateinit var myObjectTv : MyTvEntity
        try{
            val myResponseTv = JSONObject(parsingMyJsonToString("tv.json").toString())
            val list = myResponseTv.getJSONArray("tv")
            for(i in 0 until list.length()){
                val tv = list.getJSONObject(i)
                if(tv.getInt("id") == idMyTv) {
                    val tv = list.getJSONObject(i)
                    val id = tv.getInt("id")
                    val nameShow = tv.getString("nameShow")
                    val image = tv.getString("image")
                    val raiting = tv.getString("raiting")
                    val genre = tv.getString("genre")
                    val duration = tv.getString("duration")
                    val sinopsis = tv.getString("sinopsis")
                    val trailer = tv.getString("trailer")
                    val link = tv.getString("link")

                    myObjectTv = MyTvEntity(
                        id,
                        nameShow,
                        image,
                        raiting,
                        genre,
                        duration,
                        sinopsis,
                        trailer,
                        link
                    )
                }
            }
        }catch(exception:JSONException){
            exception.printStackTrace()
        }
        return myObjectTv
    }

}