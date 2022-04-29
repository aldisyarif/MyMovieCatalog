package com.bisa.submissionone.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.data.source.local.room.CatalogDao

class LocalDataSource private constructor(private val mCatalogDao: CatalogDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogDao)
    }

    fun getListCatalogMovies(): DataSource.Factory<Int, CatalogMovieEntity> = mCatalogDao.getListCatalogMovie()

    fun insertListCatalogMovies(movieList: List<CatalogMovieEntity>) = mCatalogDao.insertListCatalogMovies(movieList)

    fun getListCatalogTvShows(): DataSource.Factory<Int, CatalogTvEntity> = mCatalogDao.getListCatalogTv()

    fun insertListCatalogTvShows(tvList: List<CatalogTvEntity>) = mCatalogDao.insertListCatalogTv(tvList)

    fun getDetailCatalogMovie(movieId: String): LiveData<DetailCatalogMovieEntity> = mCatalogDao.getDetailMovie(movieId)

    fun insertDetailCatalogMovie(detailMovie: DetailCatalogMovieEntity) = mCatalogDao.insertDetailMovie(detailMovie)

    fun getDetailCatalogTvShow(tvId: String): LiveData<DetailCatalogTvEntity>  = mCatalogDao.getDetailTvShow(tvId)

    fun insertDetailCatalogTvShow(detailTv: DetailCatalogTvEntity) = mCatalogDao.insertDetailTvShow(detailTv)

    fun getListActorMovie(movieId: String): LiveData<List<ActorMovieEntity>> = mCatalogDao.getListActorsMovie(movieId)

    fun insertListActorsMovie(actors: List<ActorMovieEntity>) = mCatalogDao.insertListActorsMovie(actors)

    fun getListActorTvShow(tvId: String): LiveData<List<ActorTvEntity>> = mCatalogDao.getListActorsTvShow(tvId)

    fun insertListActorsTvShow(actors: List<ActorTvEntity>) = mCatalogDao.insertListActorsTvShow(actors)


    //FAVORITE
    fun getListFavoriteCatalogMovie(): DataSource.Factory<Int, CatalogMovieEntity> = mCatalogDao.getListFavoriteCatalogMovie()

    fun getListFavoriteCatalogTvShow(): DataSource.Factory<Int, CatalogTvEntity> = mCatalogDao.getListFavoriteCatalogTvShow()


    fun setFavoriteCatalogMovie(movie: CatalogMovieEntity, newState: Boolean){
        movie.favorite = newState
        mCatalogDao.updateFavoriteMovie(movie)
    }

    fun setFavoriteCatalogTvShow(tvShow: CatalogTvEntity, newState: Boolean){
        tvShow.favorite = newState
        mCatalogDao.updateFavoriteTvShow(tvShow)
    }

    fun getCatalogMovieForSwitch(movieId: String): LiveData<CatalogMovieEntity> = mCatalogDao.getCatalogMovieForSwitch(movieId)

    fun getCatalogTvForSwitch(tvId: String): LiveData<CatalogTvEntity> = mCatalogDao.getCatalogTvForSwitch(tvId)

}

