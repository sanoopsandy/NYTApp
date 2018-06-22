package com.example.nytapp.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.nytapp.dataBase.dao.StoryItemDao

@Database(entities = [StoryItem::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun storyItemDao(): StoryItemDao

}