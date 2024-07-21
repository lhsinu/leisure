package com.smu.leisure.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LeisureDao {
    @Query("SELECT * FROM LeisureEntity ORDER BY createddate DESC")
    fun getAll() : List<LeisureEntity>

    @Insert
    fun insertTodo(todo : LeisureEntity)

    @Delete
    fun deleteTodo(todo : LeisureEntity)

//    @Query("UPDATE LeisureEntity SET download = :download WHERE id = :id")
//    fun updateDownloadTodo(id: Int, download: Boolean)
}