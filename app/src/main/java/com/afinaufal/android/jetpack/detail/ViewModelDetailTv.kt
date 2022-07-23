package com.afinaufal.androiddasar.afinaufalsubmission_1.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource

class ViewModelDetailTv(private val myTvRepository:MyAppRepository):ViewModel(){

    var dataTvId = MutableLiveData<Int>()

    fun setSelectTv(dataTvId: Int){
        this.dataTvId.value = dataTvId
    }

    var getDataTv:LiveData<Resource<MyTvEntity>> = Transformations.switchMap(dataTvId){
        idMovie -> myTvRepository.getDetailMyTv(idMovie)
    }

    fun setFavoritMovie(){
        val myFavTv = getDataTv.value
        if(myFavTv != null){
            val myTv = myFavTv.data
            if(myTv != null){
                val value = myTv.favorit
                myTvRepository.setFavoriteTv(myTv, value)
            }
        }
    }
}