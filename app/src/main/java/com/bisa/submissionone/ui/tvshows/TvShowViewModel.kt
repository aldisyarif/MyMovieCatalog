package com.bisa.submissionone.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.vo.Resource

class TvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getTvShows() : LiveData<Resource<PagedList<CatalogTvEntity>>> = catalogRepository.getListTvShows()
}