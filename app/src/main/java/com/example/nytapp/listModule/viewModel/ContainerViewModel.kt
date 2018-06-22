package com.example.nytapp.listModule.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.nytapp.core.Constants
import com.example.nytapp.core.di.DIHandler
import com.example.nytapp.core.networking.DataResult
import com.example.nytapp.dataBase.StoryItem
import com.example.nytapp.dataManager.NYTRepository
import com.example.nytapp.utils.toLiveData
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ContainerViewModel : ViewModel() {

    init {
        DIHandler.getContainerComponent().inject(this)
    }

    @Inject
    lateinit var repo: NYTRepository

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    /*
    * Converting publish object to live data to observe updates in the result
    * */
    val postDataRepository: LiveData<DataResult<List<StoryItem>>> by lazy {
        repo.postFetchTopStoriesDataResult.toLiveData(compositeDisposable)
    }

    /*
    * Function that calls relevant api based on the selection of spinner item
    * */
    fun getResults(tab: Int) {
        if (postDataRepository.value == null)
            when (tab) {
                1 -> {
                    repo.fetchTopStories(Constants.TOP_STORY)
                }
                2 -> {
                    repo.fetchTopStories(Constants.BUSINESS)
                }
                3 -> {
                    repo.fetchTopStories(Constants.MOVIE)
                }
                else -> {
                    repo.fetchTopStories(Constants.BOOK)
                }
            }

    }

    /*
    * Clear data when viewmodel is dismissed
    * */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        DIHandler.destroyContainerComponent()
    }
}