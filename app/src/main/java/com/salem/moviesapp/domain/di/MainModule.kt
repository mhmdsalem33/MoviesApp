package com.salem.moviesapp.domain.di

import com.salem.moviesapp.data.data_source.local.database.MoviesDatabase
import com.salem.moviesapp.data.data_source.remote.api.MoviesApi
import com.salem.moviesapp.data.repo.MoviesRepositoryImpl
import com.salem.moviesapp.domain.repo.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {



    @Provides
    @Singleton
    fun provideMoviesRepo(
        moviesApi: MoviesApi,
        moviesDatabase: MoviesDatabase
    ): MoviesRepo {
        return MoviesRepositoryImpl(moviesApi, moviesDatabase)
    }
}