package com.bisa.submissionone.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditTvShowActorResponse(

	@field:SerializedName("cast")
	val cast: List<CastTvItem>,

	@field:SerializedName("id")
	val id: Int? = 0,
)

data class CastTvItem(

	@field:SerializedName("name")
	val name: String = " ",

	@field:SerializedName("profile_path")
	val profilePath: String? = " ",

	@field:SerializedName("id")
	val id: Int? = 0,
)
