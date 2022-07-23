package com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object MyLiveDataAppUtilsTest {
    fun <T> getValueMock(list: List<T>): PagedList<T> {
        val paged = mock(PagedList::class.java) as PagedList<T>
        `when`(paged[anyInt()]).then{data ->
            val indexData = data.arguments.first() as Int
            list[indexData]
        }

        `when`(paged.size).thenReturn(list.size)
        return paged
    }
}