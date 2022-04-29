package com.bisa.submissionone.data.source.local.entity.tvshow

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tb_catalog_tv_entities")
data class CatalogTvEntity (

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "dataId")
        var dataId: String,

        @ColumnInfo(name = "imagePoster")
        var imagePoster: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean

) : Parcelable