package com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyDataLocalAppSource
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.remotedata.RemoteMyApps
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.AppExecutor
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.MyLiveDataAppUtils
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesMovie
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesTv
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import androidx.paging.DataSource
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import org.mockito.Mockito.`when`

class MyAppRepositoryTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private val myRemoteRepo = mock(RemoteMyApps::class.java)
    private val localData = mock(MyDataLocalAppSource::class.java)
    private val appExe = mock(AppExecutor::class.java)
    private val myAppRepository = FakeMyAppRepository(myRemoteRepo, localData, appExe)

    private val myMovieResponse = ValuesMovie.movieData()
    private val myDetailMovie = ValuesMovie.movieData()[0]
    private val myIdMovie = myDetailMovie.id
    private val myTvResponse = ValuesTv.tvData()
    private val myDetailTv = ValuesTv.tvData()[0]
    private val myIdTv = myDetailTv.id


    @Test
    fun getAllMovie() {
        val dataMovie = mock(DataSource.Factory::class.java)as DataSource.Factory<Int, MyMovieEntity>
        `when`(localData.getAllMyMovie()).thenReturn(dataMovie)
        myAppRepository.getAllMovie()

        val myMovieEntity  = Resource.sucess(MyLiveDataAppUtilsTest.getValueMock(ValuesTv.tvData()))
        verify(localData).getAllMyMovie()
        assertNotNull(myMovieEntity.data)
        assertEquals(myMovieResponse.size.toLong(), myMovieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTv() {
        val dataMyTv = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MyTvEntity>
        `when`(localData.getAllMyTv()).thenReturn(dataMyTv)
        myAppRepository.getAllTv()

        val myTvEntity = Resource.sucess((MyLiveDataAppUtilsTest.getValueMock(ValuesTv.tvData())))
        verify(localData).getAllMyTv()
        assertNotNull(myTvEntity.data)
        assertEquals(myMovieResponse.size.toLong(), myTvEntity.data?.size?.toLong())
    }


    @Test
    fun getDetailMovie() {
        val dataDetailMovie = MutableLiveData<MyMovieEntity>()
        dataDetailMovie.value = ValuesMovie.myDetailMovieView(myIdMovie)
        `when`(localData.getDetailMyMovie(myIdMovie)).thenReturn(dataDetailMovie)

        val myDetailMovieEntity = MyLiveDataAppUtils.getValueMock(myAppRepository.getDetailMyMovie(myIdMovie))
        verify(localData).getDetailMyMovie(myIdMovie)
        assertNotNull(myDetailMovieEntity)
        assertEquals(myDetailMovie.id, myDetailMovieEntity.data?.id)
        assertEquals(myDetailMovie.duration, myDetailMovieEntity.data?.duration)
        assertEquals(myDetailMovie.genre, myDetailMovieEntity.data?.genre)
        assertEquals(myDetailMovie.link, myDetailMovieEntity.data?.link)
        assertEquals(myDetailMovie.nameMovie, myDetailMovieEntity.data?.nameMovie)
        assertEquals(myDetailMovie.sinopsis, myDetailMovieEntity.data?.sinopsis)
        assertEquals(myDetailMovie.trailer, myDetailMovieEntity.data?.trailer)
        assertEquals(myDetailMovie.year, myDetailMovieEntity.data?.year)
        assertEquals(myDetailMovie.raiting, myDetailMovieEntity.data?.raiting)
    }


    @Test
    fun getDetailTv(){
        val dataDetailTv = MutableLiveData<MyTvEntity>()
        dataDetailTv.value = ValuesTv.myDetailTvView(myIdTv)
        `when`(localData.getDetailMyTv(myIdTv)).thenReturn(dataDetailTv)

        val myDetailTvEntity = MyLiveDataAppUtils.getValueMock(myAppRepository.getDetailMyTv(myIdTv))
        verify(localData).getDetailMyTv(myIdTv)
        assertNotNull(myDetailTv)
        assertEquals(myDetailTv.trailer, myDetailTvEntity.data?.trailer)
        assertEquals(myDetailTv.sinopsis, myDetailTvEntity.data?.sinopsis)
        assertEquals(myDetailTv.raiting, myDetailTvEntity.data?.raiting)
        assertEquals(myDetailTv.trailer, myDetailTvEntity.data?.trailer)
        assertEquals(myDetailTv.nameShow, myDetailTvEntity.data?.nameShow)
        assertEquals(myDetailTv.link, myDetailTvEntity.data?.link)
        assertEquals(myDetailTv.genre, myDetailTvEntity.data?.genre)
        assertEquals(myDetailTv.duration, myDetailTvEntity.data?.duration)
    }


    @Test
    fun getFavoritMovie(){
        val dataSourceMovie = mock(DataSource.Factory::class.java)as DataSource.Factory<Int, MyMovieEntity>
        `when`(localData.getFavoritMyMovie()).thenReturn(dataSourceMovie)

        myAppRepository.getFavoriteMovie()

        val myMovieEntity = Resource.sucess(MyLiveDataAppUtilsTest.getValueMock(ValuesMovie.movieData()))
        verify(localData).getFavoritMyMovie()
        assertNotNull(myMovieEntity)
        assertEquals(myMovieResponse?.size.toLong(), myMovieEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoritTv(){
        val dataSourceTv = mock(DataSource.Factory::class.java)as DataSource.Factory<Int, MyTvEntity>
        `when`(localData.getFavoritMyTv()).thenReturn(dataSourceTv)

        myAppRepository.getFavoriteTv()

        val myTvEntity = Resource.sucess(MyLiveDataAppUtilsTest.getValueMock(ValuesTv.tvData()))
        verify(localData).getFavoritMyTv()
        assertNotNull(myTvEntity)
        assertEquals(myMovieResponse?.size.toLong(), myTvEntity.data?.size?.toLong())
    }



}