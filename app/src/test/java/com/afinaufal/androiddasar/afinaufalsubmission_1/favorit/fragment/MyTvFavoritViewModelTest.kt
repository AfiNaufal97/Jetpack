package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource
import com.afinaufal.androiddasar.afinaufalsubmission_1.tv.TvViewModel
import org.junit.Test
import androidx.lifecycle.Observer
import androidx.paging.PositionalDataSource
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesTv
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MyTvFavoritViewModelTest {

    private lateinit var viewModelMyTv:TvViewModel

    @Mock
    private lateinit var myAppRepository: MyAppRepository

    @Before
    fun setup(){
        viewModelMyTv = TvViewModel(myAppRepository)
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerTv:Observer<Resource<PagedList<MyTvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MyTvEntity>


    @Test
    fun getFavorit() {
        val myTv = PagedFavTv.snapFavTv(ValuesTv.tvData())
        val myTvExpected = MutableLiveData<Resource<PagedList<MyTvEntity>>>()
        myTvExpected.value = Resource.sucess(myTv)

        Mockito.`when`(myAppRepository.getAllTv()).thenReturn(myTvExpected)

        viewModelMyTv.getDataTv().observeForever(observerTv)
        Mockito.verify(myAppRepository).getAllTv()
        Mockito.verify(observerTv).onChanged(myTvExpected.value)

        val tvValueExpected = myTvExpected.value
        val tvActual = viewModelMyTv.getDataTv().value
        assertEquals(tvValueExpected, tvActual)
        assertEquals(tvValueExpected?.data, tvActual?.data)
        assertEquals(tvValueExpected?.data?.size, tvActual?.data?.size)
    }

    class PagedFavTv private constructor(private val tv:List<MyTvEntity>):PositionalDataSource<MyTvEntity>(){
        companion object{
            fun snapFavTv(favTv:List<MyTvEntity> = listOf()):PagedList<MyTvEntity>{
                return PagedList.Builder(PagedFavTv(favTv), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<MyTvEntity>
        ) {
            callback.onResult(tv, 0, tv.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MyTvEntity>) {
            val startTv = params.startPosition
            val endTv = params.startPosition + params.loadSize
            callback.onResult(tv.subList(startTv, endTv))
        }
    }
}