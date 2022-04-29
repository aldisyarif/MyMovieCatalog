package com.bisa.submissionone.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bisa.submissionone.data.source.local.LocalDataSource
import com.bisa.submissionone.data.source.local.entity.genre.Genres
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.data.source.remote.ApiResponse
import com.bisa.submissionone.data.source.remote.RemoteDataSource
import com.bisa.submissionone.data.source.remote.response.*
import com.bisa.submissionone.utils.AppExecutors
import com.bisa.submissionone.vo.Resource

class CatalogRepository private constructor(
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSource,
    private val appExecutors: AppExecutors
): CatalogDataSource {

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getListMovies(): LiveData<Resource<PagedList<CatalogMovieEntity>>> {

        return object : NetworkBoundResource<PagedList<CatalogMovieEntity>, List<ResultsMovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<CatalogMovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localData.getListCatalogMovies(), config).build()
            }


            override fun shouldFetch(data: PagedList<CatalogMovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovieItem>>> =
                remoteData.getListMovies()

            override fun saveCallResult(data: List<ResultsMovieItem>) {

                val movieList = ArrayList<CatalogMovieEntity>()

                for (response in data){
                    val movie = CatalogMovieEntity(
                        dataId = response.id.toString(),
                        imagePoster = response.posterPath,
                        title = response.title,
                        favorite = false
                    )
                    movieList.add(movie)
                }
                localData.insertListCatalogMovies(movieList)
            }
        }.asLiveData()

    }

    override fun getListTvShows(): LiveData<Resource<PagedList<CatalogTvEntity>>> {

        return object : NetworkBoundResource<PagedList<CatalogTvEntity>, List<ResultsTvShowItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<CatalogTvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()

                return LivePagedListBuilder(localData.getListCatalogTvShows(), config).build()
            }


            override fun shouldFetch(data: PagedList<CatalogTvEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvShowItem>>> =
                    remoteData.getListTvShows()

            override fun saveCallResult(data: List<ResultsTvShowItem>) {

                val tvShowList = ArrayList<CatalogTvEntity>()

                for (response in data){
                    val tvShow = CatalogTvEntity(
                            dataId = response.id.toString(),
                            imagePoster = response.posterPath,
                            title = response.name,
                            favorite = false
                    )
                    tvShowList.add(tvShow)
                }
                localData.insertListCatalogTvShows(tvShowList)
            }

        }.asLiveData()

    }

    override fun getDetailCatalogMovie(movieId: String): LiveData<Resource<DetailCatalogMovieEntity>> {

        return object : NetworkBoundResource<DetailCatalogMovieEntity, DetailCatalogMovieResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<DetailCatalogMovieEntity> =
                    localData.getDetailCatalogMovie(movieId)

            override fun shouldFetch(data: DetailCatalogMovieEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<DetailCatalogMovieResponse>> =
                    remoteData.getDetailCatalogMovie(movieId)

            override fun saveCallResult(data: DetailCatalogMovieResponse) {

                val hour = data.runtime / 60
                val minute = data.runtime % 60
                val duration = "${hour}H ${minute}m"

                val genreList = ArrayList<Genres>()
                for (genres in data.genres){
                    val genre = Genres(
                            id = genres.id.toString(),
                            name = genres.name
                    )
                    genreList.add(genre)
                }

                val movie = DetailCatalogMovieEntity(
                        dataId = data.id.toString(),
                        imagePoster = data.posterPath,
                        title = data.title,
                        tagline = data.tagline,
                        release_date = data.releaseDate,
                        genre =  genreList.joinToString(", ") {
                            it.name
                        },
                        score = (data.voteAverage * 10).toString(),
                        duration = duration,
                        description = data.overview
                )
                localData.insertDetailCatalogMovie(movie)
            }

        }.asLiveData()

    }

    override fun getDetailCatalogTvShow(tvId: String): LiveData<Resource<DetailCatalogTvEntity>> {

        return object : NetworkBoundResource<DetailCatalogTvEntity, DetailCatalogTvResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailCatalogTvEntity> =
                    localData.getDetailCatalogTvShow(tvId)

            override fun shouldFetch(data: DetailCatalogTvEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<DetailCatalogTvResponse>> =
                    remoteData.getDetailCatalogTvShow(tvId)

            override fun saveCallResult(data: DetailCatalogTvResponse) {

                val durationList = ArrayList<Int>()
                for (duration in data.episodeRunTime!!){
                    durationList.add(duration!!)
                }

                val genreList = ArrayList<Genres>()
                for (genres in data.genres!!){
                    val genre = Genres(
                            id = genres?.id.toString(),
                            name = genres?.name!!
                    )
                    genreList.add(genre)
                }


                val tvShow = DetailCatalogTvEntity(
                        dataId = data.id.toString(),
                        imagePoster = data.posterPath,
                        title = data.name,
                        tagline = data.tagline,
                        release_date = data.firstAirDate,
                        genre = genreList.joinToString(", ") {
                            it.name
                        },
                        score = (data.voteAverage * 10).toString(),
                        duration = durationList.joinToString(", "),
                        description = data.overview
                )
                localData.insertDetailCatalogTvShow(tvShow)
            }

        }.asLiveData()

    }

    override fun getCreditMovieActor(movieId: String): LiveData<Resource<List<ActorMovieEntity>>> {

        return object : NetworkBoundResource<List<ActorMovieEntity>, CreditMovieActorResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ActorMovieEntity>> =
                    localData.getListActorMovie(movieId)

            override fun shouldFetch(data: List<ActorMovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<CreditMovieActorResponse>> =
                    remoteData.getCreditMovieActor(movieId)

            override fun saveCallResult(data: CreditMovieActorResponse) {

                val actorList = ArrayList<ActorMovieEntity>()

                for (response in data.castMovie){
                    val actor = ActorMovieEntity(
                            imgPath = response.profilePath ?: " ",
                            name = response.name,
                            actorId = response.id.toString(),
                            movieid = data.id.toString()
                    )
                    actorList.add(actor)
                }
                localData.insertListActorsMovie(actorList)
            }


        }.asLiveData()

    }

    override fun getCreditTvShowActor(tvId: String): LiveData<Resource<List<ActorTvEntity>>> {

        return object : NetworkBoundResource<List<ActorTvEntity>, CreditTvShowActorResponse>(appExecutors){
            override fun loadFromDB(): LiveData<List<ActorTvEntity>> =
                    localData.getListActorTvShow(tvId)

            override fun shouldFetch(data: List<ActorTvEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<CreditTvShowActorResponse>> =
                    remoteData.getCreditTvShowActor(tvId)

            override fun saveCallResult(data: CreditTvShowActorResponse) {
                val actorList = ArrayList<ActorTvEntity>()

                for (response in data.cast){
                    val actor = ActorTvEntity(
                            imgPath = response.profilePath ?: " ",
                            name = response.name,
                            actorId = response.id.toString(),
                            tvid = data.id.toString()
                    )
                    actorList.add(actor)
                }

                localData.insertListActorsTvShow(actorList)
            }

        }.asLiveData()

    }

    override fun getListFavoriteMovies(): LiveData<PagedList<CatalogMovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()


        return LivePagedListBuilder(localData.getListFavoriteCatalogMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: CatalogMovieEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localData.setFavoriteCatalogMovie(movie, state)
        }

    override fun getListFavoriteTvShow(): LiveData<PagedList<CatalogTvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localData.getListFavoriteCatalogTvShow(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: CatalogTvEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localData.setFavoriteCatalogTvShow(tvShow, state)
        }

    override fun getCatalogMovieForSwitch(movieId: String): LiveData<CatalogMovieEntity> =
        localData.getCatalogMovieForSwitch(movieId)

    override fun getCatalogTvForSwitch(tvId: String): LiveData<CatalogTvEntity> =
        localData.getCatalogTvForSwitch(tvId)


}