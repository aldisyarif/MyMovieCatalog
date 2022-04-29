package com.bisa.submissionone.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity

class FavoriteTvShowViewModel(private val catalogRepository: CatalogRepository): ViewModel() {

    fun getTvShows(): LiveData<PagedList<CatalogTvEntity>> = catalogRepository.getListFavoriteTvShow()

}