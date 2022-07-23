package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource
import com.afinaufal.androiddasar.afinaufalsubmission_1.movie.ViewModelMovie
import org.junit.Test
import androidx.lifecycle.Observer
import androidx.paging.PositionalDataSource
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesMovie
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*
import java.util.concurrent.Executors


@RunWith(MockitoJUnitRunner::class)
class MyMovieFavoritViewModelTest {

    private lateinit var viewModelMovie:ViewModelMovie

    @Mock
    private lateinit var myRepository:MyAppRepository

    @Before
    fun setup(){
        viewModelMovie = ViewModelMovie(myRepository)
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerMovie:Observer<Resource<PagedList<MyMovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MyMovieEntity>


    @Test
    fun getFavorit() {
        val myMovie = PagedFavMovie.snapMovieFav(ValuesMovie.movieData())
        val myMovieExpented = MutableLiveData<Resource<PagedList<MyMovieEntity>>>()
        myMovieExpented.value = Resource.sucess(myMovie)

        Mockito.`when`(myRepository.getAllMovie()).thenReturn(myMovieExpented)

        viewModelMovie.getMovie().observeForever(observerMovie)
        Mockito.verify(myRepository).getAllMovie()
        Mockito.verify(observerMovie).onChanged(myMovieExpented.value)

        val movieValueExpented = myMovieExpented.value
        val movieActual = viewModelMovie.getMovie().value
        assertEquals(movieValueExpented, movieActual)
        assertEquals(movieValueExpented?.data, movieActual?.data)
        assertEquals(movieValueExpented?.data?.size, movieActual?.data?.size)
    }

    class PagedFavMovie private constructor(private val movie:List<MyMovieEntity>):PositionalDataSource<MyMovieEntity>(){
        companion object{
            fun snapMovieFav(favMovie:List<MyMovieEntity> = listOf()):PagedList<MyMovieEntity>{
                return PagedList.Builder(PagedFavMovie(favMovie), 10)
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
            val startMovie = params.startPosition
            val endMovie = params.startPosition + params.loadSize
            callback.onResult(movie.subList(startMovie, endMovie))
        }


    }
}