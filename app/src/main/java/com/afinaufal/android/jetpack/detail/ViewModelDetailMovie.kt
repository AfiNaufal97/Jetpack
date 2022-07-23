package com.afinaufal.androiddasar.afinaufalsubmission_1.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource

class ViewModelDetailMovie(private val myMovieRepository: MyAppRepository):ViewModel(){
    private var movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId:Int){
        this.movieId.value = movieId
    }

    var getMovie : LiveData<Resource<MyMovieEntity>> = Transformations.switchMap(movieId){ movieId ->
        myMovieRepository.getDetailMyMovie(movieId)
    }


    fun setFavoritMovie(){
        val myFavMovie = getMovie.value
        if(myFavMovie != null){
            val myMovie = myFavMovie.data
            if(myMovie != null){
                val value = myMovie.favorite
                myMovieRepository.setFavoriteMovie(myMovie, value)
            }
        }
    }
}