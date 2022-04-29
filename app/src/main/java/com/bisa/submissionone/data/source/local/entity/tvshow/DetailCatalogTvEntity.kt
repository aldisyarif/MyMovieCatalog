package com.bisa.submissionone.data.source.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_detail_tv_entities")
data class DetailCatalogTvEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "dataId")
        var dataId: String,

        @ColumnInfo(name = "imagePoster")
        var imagePoster: String? = " ",

        @ColumnInfo(name = "title")
        var title: String?,

        @ColumnInfo(name = "tagline")
        var tagline: String?,

        @ColumnInfo(name = "release_date")
        var release_date: String?,

        @ColumnInfo(name = "genre")
        var genre: String?,

        @ColumnInfo(name = "duration")
        var duration: String?,

        @ColumnInfo(name = "score")
        var score: String?,

        @ColumnInfo(name = "description")
        var description: String?
)
