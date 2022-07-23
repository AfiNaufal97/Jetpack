package com.afinaufal.androiddasar.afinaufalsubmission_1.myviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.detail.ViewModelDetailMovie
import com.afinaufal.androiddasar.afinaufalsubmission_1.detail.ViewModelDetailTv
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment.MyMovieFavoritViewModel
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment.MyTvFavoritViewModel
import com.afinaufal.androiddasar.afinaufalsubmission_1.movie.ViewModelMovie
import com.afinaufal.androiddasar.afinaufalsubmission_1.mydi.MyInjection
import com.afinaufal.androiddasar.afinaufalsubmission_1.tv.TvViewModel

class MyViewModelFactory private constructor(private val myAppRepository: MyAppRepository):ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance:MyViewModelFactory? = null

        fun getInstance(context: Context):MyViewModelFactory = instance?: synchronized(this){
            instance ?: MyViewModelFactory(MyInjection.provideToMyAppRepository(context)).apply {
                instance = this
            }
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(ViewModelMovie::class.java)->{
               return ViewModelMovie(myAppRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java)->{
                return TvViewModel(myAppRepository) as T
            }
            modelClass.isAssignableFrom(ViewModelDetailMovie::class.java)->{
                return ViewModelDetailMovie(myAppRepository) as T
            }
            modelClass.isAssignableFrom(ViewModelDetailTv::class.java)->{
                return ViewModelDetailTv(myAppRepository) as T
            }
            modelClass.isAssignableFrom(MyMovieFavoritViewModel::class.java)->{
                return MyMovieFavoritViewModel(myAppRepository) as T
            }
            modelClass.isAssignableFrom(MyTvFavoritViewModel::class.java)->{
                return MyTvFavoritViewModel(myAppRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }


}