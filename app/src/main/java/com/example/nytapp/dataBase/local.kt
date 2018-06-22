package com.example.nytapp.dataBase

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = [(Index("title"))])
data class StoryItem(val type: String,
                     @PrimaryKey val title: String,
                     val content: String,
                     val desc: String,
                     val url: String,
                     val imgUrl: String)