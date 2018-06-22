package com.example.nytapp.dataManager

import com.example.nytapp.core.Constants
import com.example.nytapp.core.networking.DataResult
import com.example.nytapp.dataBase.StoryItem
import com.example.nytapp.utils.*
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class NYTRepository(private val local: NYTBluePrint.Local,
                    private val remote: NYTBluePrint.Remote,
                    private val compositeDisposable: CompositeDisposable) : NYTBluePrint.Repository {

    /*
    * Initialize value of publish subject with a empty list
    * */
    override val postFetchTopStoriesDataResult: PublishSubject<DataResult<List<StoryItem>>> = PublishSubject.create<DataResult<List<StoryItem>>>()

    var remoteFetch = true

    /*
    * Function that checks if data is present in the local DB. If yes then it returns the object otherwise it fetches from the api.
    * */
    override fun fetchTopStories(type: String) {
        postFetchTopStoriesDataResult.loading(true)
        local.getStoryItems(type)
                .doOnBackOutOnMain()
                .subscribe({ topStory ->
                    postFetchTopStoriesDataResult.success(topStory)
                    if (remoteFetch)
                        refreshPost(type)
                    remoteFetch = false
                }, { handleError(it) })
                .addTo(compositeDisposable)
    }

    /*
    * Function that is called when data needs to be fetched from API.
    * */
    override fun refreshPost(type: String) {
        postFetchTopStoriesDataResult.loading(true)
        val response = when (type) {
            Constants.TOP_STORY -> getStoryItem()
            Constants.MOVIE -> getMovieItem()
            Constants.BUSINESS -> getBusinessItems()
            else -> {
                getBookItem()
            }
        }
        response!!.map { saveToStoryItem(it) }
                .doOnBackOutOnMain()
                .subscribe({ }, { handleError(it) })
                .addTo(compositeDisposable)
    }

    /*
    * Fetch top stories result from the api
    * */
    private fun getStoryItem(): Flowable<List<StoryItem>>? {
        return remote.getTopStories()
                .map { story ->
                    story.results.map { res ->
                        val imgUrl = if (res.multimedia.size > 0) res.multimedia[0].url else ""
                        StoryItem(type = Constants.TOP_STORY, title = res.title, content = res.abstract, desc = "", url = res.url, imgUrl = imgUrl)
                    }
                }
    }

    /*
    * Fetch top movie result from the api
    * */
    private fun getMovieItem(): Flowable<List<StoryItem>>? {
        return remote.getMovies()
                .map { story ->
                    story.results.map { res ->
                        val imgUrl = if (res.multimedia != null) res.multimedia.src else ""
                        StoryItem(type = Constants.MOVIE, title = res.displayTitle, content = res.headline, desc = res.summaryShort, url = res.link.url, imgUrl = imgUrl)
                    }
                }
    }

    /*
    * Fetch top book result from the api
    * */
    private fun getBookItem(): Flowable<List<StoryItem>>? {
        return remote.getBook()
                .map { story ->
                    story.results.map { res ->
                        val desc = res.description ?: ""
                        StoryItem(type = Constants.BOOK, title = res.title.trim(), content = res.author, desc = desc, url = "", imgUrl = "")
                    }
                }
    }

    /*
    * Fetch top business result from the api
    * */
    private fun getBusinessItems(): Flowable<List<StoryItem>>? {
        return remote.getBusiness()
                .map { story ->
                    story.results.map { res ->
                        val imgUrl = if (res.media.size > 0 && res.media[0].mediaMetadata.size > 0) res.media[0].mediaMetadata[0].url else ""
                        StoryItem(type = Constants.BUSINESS, title = res.title, content = res.abstract, desc = "", url = res.url, imgUrl = imgUrl)
                    }
                }
    }

    /*
    * Function that saves the List of data to database
    * */
    override fun saveToStoryItem(storyItem: List<StoryItem>) {
        local.saveStoryItems(storyItem)
    }

    override fun handleError(error: Throwable) {
        postFetchTopStoriesDataResult.failure(error)
    }
}