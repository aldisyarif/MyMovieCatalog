package com.bisa.submissionone.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditMovieActorResponse(
		@field:SerializedName("cast")
		val castMovie: List<CastMovieItem>,

		@field:SerializedName("id")
		val id: Int? = 0
)

data class CastMovieItem(

	@field:SerializedName("name")
	val name: String = " ",

	@field:SerializedName("profile_path")
	val profilePath: String?= " ",

	@field:SerializedName("id")
	val id: Int?= 0,

)
