package com.bisa.submissionone.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity

class FavoriteMovieViewModel(private val catalogRepository: CatalogRepository): ViewModel() {

    fun getMovies(): LiveData<PagedList<CatalogMovieEntity>> = catalogRepository.getListFavoriteMovies()

}
