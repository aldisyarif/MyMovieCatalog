package com.bisa.submissionone.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<ResultsTvShowItem>
)

data class ResultsTvShowItem(

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

)
