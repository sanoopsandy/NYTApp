package com.example.nytapp.dataManager

import com.example.nytapp.dataBase.AppDB
import com.example.nytapp.dataBase.StoryItem
import com.example.nytapp.utils.doOnBack
import io.reactivex.Completable
import io.reactivex.Flowable

/*
* Class that handles fetching and saving to db
* */
class NYTLocalHandler(private val appDB: AppDB) : NYTBluePrint.Local {
    override fun getStoryItems(type: String): Flowable<List<StoryItem>> {
        return appDB.storyItemDao().getALL(type)
    }

    override fun saveStoryItems(storyItem: List<StoryItem>) {
        Completable.fromAction { appDB.storyItemDao().insertAll(storyItem) }
                .doOnBack()
                .subscribe()
    }
}