package com.bisa.submissionone.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<ResultsMovieItem>

)

data class ResultsMovieItem(


	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,


	@field:SerializedName("id")
	val id: Int
)
