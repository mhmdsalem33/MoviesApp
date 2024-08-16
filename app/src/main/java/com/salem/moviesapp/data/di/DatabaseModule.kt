package com.salem.moviesapp.data.di

import android.content.Context
import androidx.room.Room
import com.salem.moviesapp.data.data_source.local.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context) : MoviesDatabase {
        return  Room.databaseBuilder(context ,MoviesDatabase::class.java , "movies.db").build()
    }



}