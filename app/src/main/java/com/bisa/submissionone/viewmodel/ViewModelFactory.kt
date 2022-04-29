package com.bisa.submissionone.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.di.Injection
import com.bisa.submissionone.ui.detail.DetailViewModel
import com.bisa.submissionone.ui.favorite.movie.FavoriteMovieViewModel
import com.bisa.submissionone.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.bisa.submissionone.ui.movies.MovieViewModel
import com.bisa.submissionone.ui.tvshows.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogRepository: CatalogRepository):  ViewModelProvider.NewInstanceFactory() {

        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mCatalogRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mCatalogRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mCatalogRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                return FavoriteMovieViewModel(mCatalogRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                return FavoriteTvShowViewModel(mCatalogRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

}