package com.bisa.submissionone.data.source.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_actors_tv_entities")
data class ActorTvEntity(
        @ColumnInfo(name = "imagePath")
        var imgPath: String?,

        @ColumnInfo(name = "name")
        var name: String,

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "actorId")
        var actorId: String,

        @NonNull
        @ColumnInfo(name = "tvId")
        var tvid: String,
)
