package com.afinaufal.androiddasar.afinaufalsubmission_1.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource

class TvViewModel(private val myAppRepository: MyAppRepository):ViewModel(){

    fun getDataTv():LiveData<Resource<PagedList<MyTvEntity>>> = myAppRepository.getAllTv()

}