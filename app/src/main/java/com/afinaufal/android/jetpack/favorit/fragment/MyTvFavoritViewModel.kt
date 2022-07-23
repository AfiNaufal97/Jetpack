package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository

class MyTvFavoritViewModel(private val repository: MyAppRepository):ViewModel(){
    fun getFavorit():LiveData<PagedList<MyTvEntity>>{
        return repository.getFavoriteTv()
    }
}