package com.afinaufal.androiddasar.afinaufalsubmission_1.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesTv
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelDetailTvTest {

    private lateinit var viewModelDetailTv:ViewModelDetailTv
    private val myViewModelTv = ValuesTv.tvData()[0]
    private val mytvById = myViewModelTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var myAppRepository: MyAppRepository

    @Mock
    private lateinit var myTvObserver: Observer<Resource<MyTvEntity>>

    @Before
    fun setup(){
        viewModelDetailTv = ViewModelDetailTv(myAppRepository)
        viewModelDetailTv.setSelectTv(mytvById)
    }

    @Test
    fun getDataTvId() {
        val myTvData = Resource.sucess(ValuesTv.myDetailTvWithFavorit(myViewModelTv, true))
        val myTv = MutableLiveData<Resource<MyTvEntity>>()
        myTv.value = myTvData
        `when`(myAppRepository.getDetailMyTv(mytvById)).thenReturn(myTv)
        viewModelDetailTv.getDataTv.observeForever(myTvObserver)
        verify(myTvObserver).onChanged(myTvData)

    }

    @Test
    fun favoriteTv(){
        val favTv = MutableLiveData<Resource<MyTvEntity>>()
        favTv.value = Resource.sucess(ValuesTv.myDetailTvWithFavorit(myViewModelTv, true))

        `when`(myAppRepository.getDetailMyTv(mytvById)).thenReturn(favTv)
        viewModelDetailTv.setFavoritMovie()
        viewModelDetailTv.getDataTv.observeForever(myTvObserver)
        verify(myAppRepository).getDetailMyTv(mytvById)
        verify(myTvObserver).onChanged(favTv.value)

        val tvValue = favTv.value
        val tvValueActual = viewModelDetailTv.getDataTv.value

        assertEquals(tvValue, tvValueActual)
    }
}