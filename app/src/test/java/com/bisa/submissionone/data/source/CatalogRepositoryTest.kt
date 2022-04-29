package com.bisa.submissionone.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bisa.submissionone.data.source.local.LocalDataSource
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.data.source.remote.RemoteDataSource
import com.bisa.submissionone.utils.AppExecutors
import com.bisa.submissionone.utils.DataDummy
import com.bisa.submissionone.utils.LiveDataTestUtils
import com.bisa.submissionone.utils.PagedListUtil
import com.bisa.submissionone.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`


import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)


    private val catalogRepository = FakeCatalogRepository(remote, local, appExecutors)

    private val movies = DataDummy.generateListMovie()
    private val movieId = movies[1].dataId
    private val tvShows = DataDummy.generateListTvShow()
    private val tvId = tvShows[0].dataId

    private val detailMovie = DataDummy.generateDetailMovie()
    private val detailTv = DataDummy.generateDetailTvShow()

    private val actorMovie = DataDummy.generateActorMovie()
    private val actorTv = DataDummy.generateActorTvShow()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getListMovies() {

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogMovieEntity>
        `when`(local.getListCatalogMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getListMovies()

        val moviesEntities = Resource.success(PagedListUtil.mocPagedList(DataDummy.generateListMovie()))
        verify(local).getListCatalogMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(movies.size.toLong(), moviesEntities.data?.size?.toLong())


    }

    @Test
    fun getListTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogTvEntity>
        `when`(local.getListCatalogTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getListTvShows()

        val tvShowsEntities = Resource.success(PagedListUtil.mocPagedList(DataDummy.generateListTvShow()))
        verify(local).getListCatalogTvShows()
        assertNotNull(tvShowsEntities.data)
        assertEquals(tvShows.size.toLong(), tvShowsEntities.data?.size?.toLong())


    }

    @Test
    fun getDetailCatalogMovie() {

        val dummyMovie = MutableLiveData<DetailCatalogMovieEntity>()
        dummyMovie.value = detailMovie
        `when`(local.getDetailCatalogMovie(movieId)).thenReturn(dummyMovie)

        val detailMovieEntities = LiveDataTestUtils.getValue(catalogRepository.getDetailCatalogMovie(movieId))
        verify(local).getDetailCatalogMovie(movieId)
        assertNotNull(detailMovieEntities.data)
        assertNotNull(detailMovieEntities.data?.title)
        assertEquals(detailMovie.title, detailMovieEntities.data?.title)



    }

    @Test
    fun getDetailCatalogTvShow() {

        val dummyTv = MutableLiveData<DetailCatalogTvEntity>()
        dummyTv.value = detailTv
        `when`(local.getDetailCatalogTvShow(tvId)).thenReturn(dummyTv)

        val detailTvEntities = LiveDataTestUtils.getValue(catalogRepository.getDetailCatalogTvShow(tvId))
        verify(local).getDetailCatalogTvShow(tvId)

        assertNotNull(detailTvEntities.data)
        assertNotNull(detailTvEntities.data?.title)
        assertEquals(detailTv.title, detailTvEntities.data?.title)
    }


    @Test
    fun getCreditMovieActor() {

        val dummyActorsMovie = MutableLiveData<List<ActorMovieEntity>>()
        dummyActorsMovie.value = actorMovie

        `when`(local.getListActorMovie(movieId)).thenReturn(dummyActorsMovie)

        val movieActorEntities = LiveDataTestUtils.getValue(catalogRepository.getCreditMovieActor(movieId))
        verify(local).getListActorMovie(movieId)

        assertNotNull(movieActorEntities.data)
        assertEquals(actorMovie.size.toLong(), movieActorEntities.data?.size?.toLong())

    }

    @Test
    fun getCreditTvShowActor() {
        val dummyActorTv = MutableLiveData<List<ActorTvEntity>>()
        dummyActorTv.value = actorTv

        `when`(local.getListActorTvShow(tvId)).thenReturn(dummyActorTv)

        val tvActorEntities = LiveDataTestUtils.getValue(catalogRepository.getCreditTvShowActor(tvId))
        verify(local).getListActorTvShow(tvId)

        assertNotNull(tvActorEntities)
        assertEquals(actorTv.size.toLong(), tvActorEntities.data?.size?.toLong())

    }

    @Test
    fun getListFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogMovieEntity>
        `when`(local.getListFavoriteCatalogMovie()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteMovies()

        val moviesFavoriteEntities = Resource.success(PagedListUtil.mocPagedList(DataDummy.generateListMovie()))
        verify(local).getListFavoriteCatalogMovie()
        assertNotNull(moviesFavoriteEntities.data)
        assertEquals(movies.size.toLong(), moviesFavoriteEntities.data?.size?.toLong())
    }

    @Test
    fun getListFavoriteTvShow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogTvEntity>
        `when`(local.getListFavoriteCatalogTvShow()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteTvShow()

        val tvShowsFavoriteEntities = Resource.success(PagedListUtil.mocPagedList(DataDummy.generateListTvShow()))
        verify(local).getListFavoriteCatalogTvShow()
        assertNotNull(tvShowsFavoriteEntities.data)
        assertEquals(tvShows.size.toLong(), tvShowsFavoriteEntities.data?.size?.toLong())
    }

}