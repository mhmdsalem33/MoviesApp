package com.salem.moviesapp.data.data_source.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salem.moviesapp.domain.models.MoviesResultModel

@Dao
interface MoviesListDao {

//    @Upsert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll( list : List<MoviesResultModel>)

    @Query("Select * FROM moviesList")
    fun pagingSource() :  PagingSource< Int , MoviesResultModel >


    @Query("DELETE FROM moviesList")
    suspend fun clearAll()

}