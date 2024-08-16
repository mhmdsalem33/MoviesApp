package com.salem.moviesapp.data.data_source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salem.moviesapp.domain.models.MoviesResultModel


@Database( entities = [MoviesResultModel::class] , version = 1 )
abstract class MoviesDatabase :RoomDatabase() {
    abstract val dao : MoviesListDao
}