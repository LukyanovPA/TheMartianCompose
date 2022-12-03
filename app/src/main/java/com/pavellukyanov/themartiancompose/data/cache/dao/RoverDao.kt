package com.pavellukyanov.themartiancompose.data.cache.dao

import androidx.room.*
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import kotlinx.coroutines.flow.Flow

@Dao
interface RoverDao {
    @Query("SELECT * FROM rovers WHERE roverName = :roverName")
    fun load(roverName: String): Flow<Rover>

    @Query("SELECT * FROM rovers")
    fun loadAll(): Flow<List<Rover>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roverInfoEntityList: List<Rover>)
}