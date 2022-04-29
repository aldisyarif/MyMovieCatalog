package com.bisa.submissionone.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.utils.DataDummy
import com.bisa.submissionone.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel


    private val dummyDetailMovie = DataDummy.generateDetailMovie()
    private val dataIdMovie = dummyDetailMovie.dataId

    private val dummyDetailTv =  DataDummy.generateDetailTvShow()
    private val dataIdTv = dummyDetailTv.dataId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<DetailCatalogMovieEntity>>

    @Mock
    private lateinit var observerTv: Observer<Resource<DetailCatalogTvEntity>>

    @Mock
    private lateinit var observerActorMovie: Observer<Resource<List<ActorMovieEntity>>>

    @Mock
    private lateinit var observerActorTv: Observer<Resource<List<ActorTvEntity>>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)

        viewModel.setSelectedMovie(dataIdMovie)
        viewModel.setSelectedTvShow(dataIdTv)
    }

    @Test
    fun getContentTvShow() {

        val dummyTv = Resource.success(DataDummy.generateDetailTvShow())
        val tv = MutableLiveData<Resource<DetailCatalogTvEntity>>()
        tv.value = dummyTv

        `when`(catalogRepository.getDetailCatalogTvShow(dataIdTv)).thenReturn(tv)
        val detailTvEntity = viewModel.getContentTvShow().value

        verify<CatalogRepository>(catalogRepository).getDetailCatalogTvShow(dataIdTv)

        assertNotNull(detailTvEntity?.data)
        assertEquals(dummyDetailTv.dataId, detailTvEntity?.data?.dataId)
        assertEquals(dummyDetailTv.imagePoster, detailTvEntity?.data?.imagePoster)
        assertEquals(dummyDetailTv.tagline, detailTvEntity?.data?.tagline)
        assertEquals(dummyDetailTv.title, detailTvEntity?.data?.title)
        assertEquals(dummyDetailTv.genre?.length, detailTvEntity?.data?.genre?.length)
        assertEquals(dummyDetailTv.release_date, detailTvEntity?.data?.release_date)
        assertEquals(dummyDetailTv.duration, detailTvEntity?.data?.duration)
        assertEquals(dummyDetailTv.score, detailTvEntity?.data?.score)
        assertEquals(dummyDetailTv.description, detailTvEntity?.data?.description)

        viewModel.getContentTvShow().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)

    }


    @Test
    fun getContentMovie() {

        val dummyMovie = Resource.success(DataDummy.generateDetailMovie())

        val movie = MutableLiveData<Resource<DetailCatalogMovieEntity>>()
        movie.value = dummyMovie

        `when`(catalogRepository.getDetailCatalogMovie(dataIdMovie)).thenReturn(movie)
        val detailMovieEntity = viewModel.getContentMovie().value

        verify<CatalogRepository>(catalogRepository).getDetailCatalogMovie(dataIdMovie)

        assertNotNull(detailMovieEntity)
        assertEquals(dummyDetailMovie.dataId, detailMovieEntity?.data?.dataId)
        assertEquals(dummyDetailMovie.imagePoster, detailMovieEntity?.data?.imagePoster)
        assertEquals(dummyDetailMovie.tagline, detailMovieEntity?.data?.tagline)
        assertEquals(dummyDetailMovie.title, detailMovieEntity?.data?.title)
        assertEquals(dummyDetailMovie.genre?.length, detailMovieEntity?.data?.genre?.length)
        assertEquals(dummyDetailMovie.release_date, detailMovieEntity?.data?.release_date)
        assertEquals(dummyDetailMovie.duration, detailMovieEntity?.data?.duration)
        assertEquals(dummyDetailMovie.score, detailMovieEntity?.data?.score)
        assertEquals(dummyDetailMovie.description, detailMovieEntity?.data?.description)

        viewModel.getContentMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)

    }



   @Test
   fun getActorMovie(){
       val dummyActorMovie = Resource.success(DataDummy.generateActorMovie())
       val actor = MutableLiveData<Resource<List<ActorMovieEntity>>>()

       actor.value = dummyActorMovie

       `when`(catalogRepository.getCreditMovieActor(dataIdMovie)).thenReturn(actor)
       val actorEntities = viewModel.getActorMovie().value
       verify<CatalogRepository>(catalogRepository).getCreditMovieActor(dataIdMovie)

       assertNotNull(actorEntities)
       assertEquals(5, actorEntities?.data?.size)

       viewModel.getActorMovie().observeForever(observerActorMovie)
       verify(observerActorMovie).onChanged(dummyActorMovie)
   }


   @Test
   fun getActorTvShow(){
       val dummyActorTv = Resource.success(DataDummy.generateActorTvShow())
       val actor = MutableLiveData<Resource<List<ActorTvEntity>>>()

       actor.value = dummyActorTv

       `when`(catalogRepository.getCreditTvShowActor(dataIdTv)).thenReturn(actor)
       val actorEntities = viewModel.getActorTvShow().value
       verify<CatalogRepository>(catalogRepository).getCreditTvShowActor(dataIdTv)

       assertNotNull(actorEntities)
       assertEquals(5, actorEntities?.data?.size)

       viewModel.getActorTvShow().observeForever(observerActorTv)
       verify(observerActorTv).onChanged(dummyActorTv)
   }
}