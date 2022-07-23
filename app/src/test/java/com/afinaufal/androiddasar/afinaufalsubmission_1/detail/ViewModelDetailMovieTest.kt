package com.afinaufal.androiddasar.afinaufalsubmission_1.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesMovie
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
class ViewModelDetailMovieTest {
    private lateinit var viewModelDetailMovie:ViewModelDetailMovie
    private val myViewModelMovie = ValuesMovie.movieData()[0]
    private val myMovieById  = myViewModelMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var myAppRepository: MyAppRepository

    @Mock
    private lateinit var myMovieObserver:Observer<Resource<MyMovieEntity>>

    @Before
    fun setup(){
        viewModelDetailMovie = ViewModelDetailMovie(myAppRepository)
        viewModelDetailMovie.setSelectedMovie(myMovieById)
    }

    @Test
    fun getDetail(){
        val dataDetailMovie = Resource.sucess(ValuesMovie.myDetailMovieWithFavorit(myViewModelMovie, true))
        val myMovie = MutableLiveData<Resource<MyMovieEntity>>()

        myMovie.value = dataDetailMovie
        `when`(myAppRepository.getDetailMyMovie(myMovieById)).thenReturn(myMovie)
        viewModelDetailMovie.getMovie.observeForever(myMovieObserver)
        verify(myMovieObserver).onChanged(dataDetailMovie)
    }

    @Test
    fun favoritMovie(){
        val favMovie = MutableLiveData<Resource<MyMovieEntity>>()
        favMovie.value = Resource.sucess(ValuesMovie.myDetailMovieWithFavorit(myViewModelMovie, true))

        `when`(myAppRepository.getDetailMyMovie(myMovieById)).thenReturn(favMovie)
        viewModelDetailMovie.setFavoritMovie()
        viewModelDetailMovie.getMovie.observeForever(myMovieObserver)
        verify(myAppRepository).getDetailMyMovie(myMovieById)
        verify(myMovieObserver).onChanged(favMovie.value)

        val movieValue = favMovie.value
        val myMovieActual = viewModelDetailMovie.getMovie.value

        assertEquals(movieValue, myMovieActual)
    }
}