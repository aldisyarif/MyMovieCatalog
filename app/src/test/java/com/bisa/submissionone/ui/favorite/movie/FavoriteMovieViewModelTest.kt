package com.bisa.submissionone.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<CatalogMovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<CatalogMovieEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(catalogRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(10)

        val movies = MutableLiveData<PagedList<CatalogMovieEntity>>()

        movies.value = dummyMovie

        `when`(catalogRepository.getListFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify<CatalogRepository>(catalogRepository).getListFavoriteMovies()

        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}