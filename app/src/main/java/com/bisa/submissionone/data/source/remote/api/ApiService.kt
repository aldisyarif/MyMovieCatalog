package com.bisa.submissionone.data.source.remote.api

import com.bisa.submissionone.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ) : Call<MoviesResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ) : Call<TvShowsResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<DetailCatalogMovieResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<DetailCatalogTvResponse>

    @GET("movie/{movie_id}/credits")
    fun getCreditMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<CreditMovieActorResponse>

    @GET("tv/{tv_id}/credits")
    fun getCreditTvShow(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<CreditTvShowActorResponse>
}