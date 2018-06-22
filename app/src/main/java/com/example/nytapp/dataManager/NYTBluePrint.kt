package com.example.nytapp.dataManager

import com.example.nytapp.core.networking.DataResult
import com.example.nytapp.dataBase.StoryItem
import com.example.nytapp.dataBase.models.book.BookResponse
import com.example.nytapp.dataBase.models.business.BusinessResponse
import com.example.nytapp.dataBase.models.movie.MovieResponse
import com.example.nytapp.dataBase.models.topStory.TopStory
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

/*
* Blueprint of all the functions that will be used to fetch, store and access data objects
* */
interface NYTBluePrint {
    interface Repository {
        val postFetchTopStoriesDataResult: PublishSubject<DataResult<List<StoryItem>>>
        fun fetchTopStories(type: String)
        fun refreshPost(type: String)
        fun saveToStoryItem(storyItem: List<StoryItem>)
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun getTopStories(): Flowable<TopStory>
        fun getMovies(): Flowable<MovieResponse>
        fun getBusiness(): Flowable<BusinessResponse>
        fun getBook(): Flowable<BookResponse>
    }

    interface Local {
        fun getStoryItems(type: String): Flowable<List<StoryItem>>
        fun saveStoryItems(storyItem: List<StoryItem>)
    }

}