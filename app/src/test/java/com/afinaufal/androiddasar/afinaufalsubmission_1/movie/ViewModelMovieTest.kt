package com.afinaufal.androiddasar.afinaufalsubmission_1.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesMovie
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class ViewModelMovieTest {

    private lateinit var viewModelMovie:ViewModelMovie

    @get:Rule
    var instantTaskExecutonRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var myRepository: MyAppRepository


    @Mock
    private lateinit var observer:Observer<Resource<PagedList<MyMovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MyMovieEntity>


    @Before
    fun setup() {
        viewModelMovie = ViewModelMovie(myRepository)
    }

    @Test
    fun getMovie(){
        val mDummy = MoviePagedDataSources.movieSnapshot(ValuesMovie.movieData())
        val myMovie = MutableLiveData<Resource<PagedList<MyMovieEntity>>>()
        myMovie.value = Resource.sucess(mDummy)

        `when`(myRepository.getAllMovie()).thenReturn(myMovie)
        viewModelMovie.getMovie().observeForever(observer)
        verify(myRepository).getAllMovie()
        verify(observer).onChanged(myMovie.value)

        val movieValue = myMovie.value
        val movieValueEctual = viewModelMovie.getMovie().value
        assertEquals(movieValue, movieValueEctual)
        assertEquals(movieValue?.data,movieValueEctual?.data)
        assertEquals(movieValue?.data?.size, movieValueEctual?.data?.size)
    }

    class MoviePagedDataSources private constructor(private val movie:List<MyMovieEntity>):PositionalDataSource<MyMovieEntity>(){
        companion object{
            fun movieSnapshot(movieList:List<MyMovieEntity> = listOf()):PagedList<MyMovieEntity>{
                return PagedList.Builder(MoviePagedDataSources(movieList), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<MyMovieEntity>
        ) {
            callback.onResult(movie, 0, movie.size)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<MyMovieEntity>
        ) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(movie.subList(start, end))
        }

    }
}