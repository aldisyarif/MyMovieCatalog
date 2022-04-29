package com.bisa.submissionone.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity

@Database(entities = [
    CatalogMovieEntity::class,
    CatalogTvEntity::class,
    DetailCatalogMovieEntity::class,
    DetailCatalogTvEntity::class,

    ActorMovieEntity::class,
    ActorTvEntity::class
    ],
    version = 1,
    exportSchema = false)
abstract class CatalogDatabase: RoomDatabase() {

    abstract fun catalogDao(): CatalogDao

    companion object {

        @Volatile
        private var INSTANCE: CatalogDatabase? = null

        fun getInstance(context: Context): CatalogDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogDatabase::class.java,
                    "Catalog.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }

}