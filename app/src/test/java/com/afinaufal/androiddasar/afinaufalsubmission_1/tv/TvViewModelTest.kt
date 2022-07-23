package com.afinaufal.androiddasar.afinaufalsubmission_1.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
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
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModelTv:TvViewModel

    @get:Rule
    var instantTaskExecutonRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var myRepository: MyAppRepository


    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MyTvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MyTvEntity>

    @Before
    fun setup() {
        viewModelTv = TvViewModel(myRepository)
    }

    @Test
    fun getTv(){
        val mDummy = TvPagedDataSource.tvSnapshot(ValuesTv.tvData())
        val myTv = MutableLiveData<Resource<PagedList<MyTvEntity>>>()
        myTv.value = Resource.sucess(mDummy)

        `when`(myRepository.getAllTv()).thenReturn(myTv)
        viewModelTv.getDataTv().observeForever(observer)
        verify(myRepository).getAllTv()
        verify(observer).onChanged(myTv.value)
        val tvValue = myTv.value
        val tvValueActual = viewModelTv.getDataTv().value
        assertEquals(tvValue, tvValueActual)
        assertEquals(tvValue?.data, tvValueActual?.data)
        assertEquals(tvValue?.data?.size, tvValue?.data?.size)
    }

    class TvPagedDataSource private constructor(private val tv:List<MyTvEntity>):PositionalDataSource<MyTvEntity>(){
        companion object{
            fun tvSnapshot(tvList:List<MyTvEntity> = listOf()):PagedList<MyTvEntity>{
                return PagedList.Builder(TvPagedDataSource(tvList), 10)
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
            val tvStart = params.startPosition
            val tvStop = params.startPosition + params.loadSize
            callback.onResult(tv.subList(tvStart, tvStop))
        }
    }
}