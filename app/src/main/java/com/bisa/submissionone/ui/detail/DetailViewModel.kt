package com.bisa.submissionone.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.vo.Resource

class DetailViewModel(private val catalogRepository: CatalogRepository): ViewModel() {

    private val dataIdMovie = MutableLiveData<String>()
    private val dataIdTvShow = MutableLiveData<String>()

    fun setSelectedMovie(id: String){
        this.dataIdMovie.value = id
    }

    fun setSelectedTvShow(id: String){
        this.dataIdTvShow.value = id
    }

    fun setFavoriteMovie(movie: CatalogMovieEntity, state: Boolean){
        val newState = !state
        catalogRepository.setFavoriteMovie(movie, newState)
    }
    fun setFavoriteTvShow(tv: CatalogTvEntity, state: Boolean){
        val newState = !state
        catalogRepository.setFavoriteTvShow(tv, newState)
    }

    var switchFavoriteCatalogMovie: LiveData<CatalogMovieEntity> = Transformations.switchMap(dataIdMovie){ mMovieId ->
        catalogRepository.getCatalogMovieForSwitch(mMovieId)
    }
    var switchFavoriteCatalogTvShow: LiveData<CatalogTvEntity> = Transformations.switchMap(dataIdTvShow){ mTvShowId ->
        catalogRepository.getCatalogTvForSwitch(mTvShowId)
    }


    fun getContentMovie(): LiveData<Resource<DetailCatalogMovieEntity>> = catalogRepository.getDetailCatalogMovie(dataIdMovie.value!!)

    fun getContentTvShow(): LiveData<Resource<DetailCatalogTvEntity>> = catalogRepository.getDetailCatalogTvShow(dataIdTvShow.value!!)

    fun getActorMovie(): LiveData<Resource<List<ActorMovieEntity>>> = catalogRepository.getCreditMovieActor(dataIdMovie.value!!)

    fun getActorTvShow(): LiveData<Resource<List<ActorTvEntity>>> = catalogRepository.getCreditTvShowActor(dataIdTvShow.value!!)
}