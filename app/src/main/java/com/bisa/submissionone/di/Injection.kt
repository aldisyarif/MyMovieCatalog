package com.bisa.submissionone.di

import android.content.Context
import com.bisa.submissionone.data.CatalogRepository
import com.bisa.submissionone.data.source.local.LocalDataSource
import com.bisa.submissionone.data.source.local.room.CatalogDatabase
import com.bisa.submissionone.data.source.remote.RemoteDataSource
import com.bisa.submissionone.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): CatalogRepository {

        val database = CatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()

        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}