package com.bisa.submissionone.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity

@Dao
interface CatalogDao {

    @Query("SELECT * FROM tb_catalog_movie_entities")
    fun getListCatalogMovie(): DataSource.Factory<Int, CatalogMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListCatalogMovies(movies: List<CatalogMovieEntity>)

    @Query("SELECT * FROM tb_catalog_tv_entities")
    fun getListCatalogTv(): DataSource.Factory<Int, CatalogTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListCatalogTv(mtvShows: List<CatalogTvEntity>)

    @Query("SELECT * FROM tb_detail_movie_entities WHERE dataId = :movieId")
    fun getDetailMovie(movieId: String): LiveData<DetailCatalogMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(detailMovie: DetailCatalogMovieEntity)

    @Query("SELECT * FROM tb_detail_tv_entities WHERE dataId = :tvId")
    fun getDetailTvShow(tvId: String): LiveData<DetailCatalogTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTvShow(detailTvShow: DetailCatalogTvEntity)

    @Query("SELECT * FROM tb_actors_movie_entities WHERE movied = :movieId")
    fun getListActorsMovie(movieId: String): LiveData<List<ActorMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListActorsMovie(actors: List<ActorMovieEntity>)

    @Query("SELECT * FROM tb_actors_tv_entities WHERE tvId = :tvId")
    fun getListActorsTvShow(tvId: String): LiveData<List<ActorTvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListActorsTvShow(actors: List<ActorTvEntity>)


    @Query("SELECT * FROM tb_catalog_movie_entities WHERE favorite = 1")
    fun getListFavoriteCatalogMovie(): DataSource.Factory<Int, CatalogMovieEntity>

    @Update
    fun updateFavoriteMovie(movie: CatalogMovieEntity)

    @Query("SELECT * FROM tb_catalog_tv_entities WHERE favorite = 1")
    fun getListFavoriteCatalogTvShow(): DataSource.Factory<Int, CatalogTvEntity>

    @Update
    fun updateFavoriteTvShow(tvShow: CatalogTvEntity)

    @Query("SELECT * FROM tb_catalog_movie_entities WHERE dataId = :movieId")
    fun getCatalogMovieForSwitch(movieId: String): LiveData<CatalogMovieEntity>

    @Query("SELECT * FROM tb_catalog_tv_entities WHERE dataId = :tvId")
    fun getCatalogTvForSwitch(tvId: String): LiveData<CatalogTvEntity>


}