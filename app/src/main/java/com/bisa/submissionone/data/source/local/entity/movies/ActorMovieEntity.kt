package com.bisa.submissionone.data.source.local.entity.movies

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "tb_actors_movie_entities")
data class ActorMovieEntity(

    @ColumnInfo(name = "imagePath")
    var imgPath: String?,

    @ColumnInfo(name = "name")
    var name: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "actorId")
    var actorId: String,

    @NonNull
    @ColumnInfo(name = "movied")
    var movieid: String,
)