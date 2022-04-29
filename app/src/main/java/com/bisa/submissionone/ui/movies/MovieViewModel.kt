package com.bisa.submissionone.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.vo.Resource

class MovieViewModel(private val catalogRepository: CatalogRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<CatalogMovieEntity>>> = catalogRepository.getListMovies()
}