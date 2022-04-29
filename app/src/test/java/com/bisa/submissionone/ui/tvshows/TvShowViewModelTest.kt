package com.bisa.submissionone.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<CatalogTvEntity>>>

    @Mock
    private lateinit var tvPagedList: PagedList<CatalogTvEntity>


    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun getTvShows() {

        val dummyTvShow = Resource.success(tvPagedList)
        `when`(dummyTvShow.data?.size).thenReturn(10)


        val tv = MutableLiveData<Resource<PagedList<CatalogTvEntity>>>()

        tv.value = dummyTvShow

        `when`(catalogRepository.getListTvShows()).thenReturn(tv)
        val tvEntities = viewModel.getTvShows().value?.data
        verify<CatalogRepository>(catalogRepository).getListTvShows()

        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)

    }
}