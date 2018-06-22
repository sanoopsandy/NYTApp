package com.example.nytapp.dataBase.remote

import com.example.nytapp.core.Constants
import com.example.nytapp.dataBase.models.book.BookResponse
import com.example.nytapp.dataBase.models.business.BusinessResponse
import com.example.nytapp.dataBase.models.movie.MovieResponse
import com.example.nytapp.dataBase.models.topStory.TopStory
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("/svc/topstories/v2/home.json")
    fun getTopStories(@Query("api-key") apiKey: String = Constants.API_KEY): Flowable<TopStory>

    @GET("/svc/mostpopular/v2/mostemailed/Business Day/7.json")
    fun getBusinessStories(@Query("api-key") apiKey: String = Constants.API_KEY): Flowable<BusinessResponse>

    @GET("/svc/books/v3/lists/best-sellers/history.json")
    fun getBookStories(@Query("api-key") apiKey: String = Constants.API_KEY): Flowable<BookResponse>

    @GET("/svc/movies/v2/reviews/search.json")
    fun getMovieStories(@Query("api-key") apiKey: String = Constants.API_KEY): Flowable<MovieResponse>

}