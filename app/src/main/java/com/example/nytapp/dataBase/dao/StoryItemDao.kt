package com.example.nytapp.dataBase.dao

import android.arch.persistence.room.*
import com.example.nytapp.dataBase.StoryItem
import io.reactivex.Flowable

@Dao
interface StoryItemDao {
    @Query("SELECT * FROM StoryItem WHERE type = :type")
    fun getALL(type: String): Flowable<List<StoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<StoryItem>)

    @Delete
    fun delete(user: StoryItem)
}