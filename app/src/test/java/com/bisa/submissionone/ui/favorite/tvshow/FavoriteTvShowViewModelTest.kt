package com.bisa.submissionone.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<CatalogTvEntity>>

    @Mock
    private lateinit var pagedList: PagedList<CatalogTvEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(catalogRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTv = pagedList
        `when`(dummyTv.size).thenReturn(10)

        val tvShows = MutableLiveData<PagedList<CatalogTvEntity>>()

        tvShows.value = dummyTv

        `when`(catalogRepository.getListFavoriteTvShow()).thenReturn(tvShows)
        val tvEntities = viewModel.getTvShows().value
        verify<CatalogRepository>(catalogRepository).getListFavoriteTvShow()

        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}