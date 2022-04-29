package com.bisa.submissionone.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.vo.Resource

interface CatalogDataSource {

    fun getListMovies(): LiveData<Resource<PagedList<CatalogMovieEntity>>>

    fun getListTvShows(): LiveData<Resource<PagedList<CatalogTvEntity>>>

    fun getDetailCatalogMovie(movieId: String): LiveData<Resource<DetailCatalogMovieEntity>>

    fun getDetailCatalogTvShow(tvId: String): LiveData<Resource<DetailCatalogTvEntity>>

    fun getCreditMovieActor(movieId: String): LiveData<Resource<List<ActorMovieEntity>>>

    fun getCreditTvShowActor(tvId: String): LiveData<Resource<List<ActorTvEntity>>>

    //FAVORITE
    fun getListFavoriteMovies(): LiveData<PagedList<CatalogMovieEntity>>

    fun setFavoriteMovie(movie: CatalogMovieEntity, state: Boolean)

    fun getListFavoriteTvShow(): LiveData<PagedList<CatalogTvEntity>>

    fun setFavoriteTvShow(tvShow: CatalogTvEntity, state: Boolean)

    fun getCatalogMovieForSwitch(movieId: String): LiveData<CatalogMovieEntity>

    fun getCatalogTvForSwitch(tvId: String): LiveData<CatalogTvEntity>
}