package com.afinaufal.androiddasar.afinaufalsubmission_1.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource

class ViewModelMovie(private val myAppRepository: MyAppRepository):ViewModel() {
    fun getMovie():LiveData<Resource<PagedList<MyMovieEntity>>> = myAppRepository.getAllMovie()
}