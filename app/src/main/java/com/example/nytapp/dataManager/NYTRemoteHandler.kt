package com.example.nytapp.dataManager

import com.example.nytapp.dataBase.models.book.BookResponse
import com.example.nytapp.dataBase.models.business.BusinessResponse
import com.example.nytapp.dataBase.models.movie.MovieResponse
import com.example.nytapp.dataBase.models.topStory.TopStory
import com.example.nytapp.dataBase.remote.PostService
import io.reactivex.Flowable

/*
* Class responsible for making api calls
* */
class NYTRemoteHandler(private val postService: PostService) : NYTBluePrint.Remote {
    override fun getMovies(): Flowable<MovieResponse> {
        return postService.getMovieStories()
    }

    override fun getBusiness(): Flowable<BusinessResponse> {
        return postService.getBusinessStories()
    }

    override fun getBook(): Flowable<BookResponse> {
        return postService.getBookStories()
    }

    override fun getTopStories(): Flowable<TopStory> {
        return postService.getTopStories()
    }
}