package com.example.nytapp.listModule.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.nytapp.adapters.BaseRecyclerAdapter
import com.example.nytapp.core.di.BaseComponent
import com.example.nytapp.dataBase.AppDB
import com.example.nytapp.dataBase.remote.PostService
import com.example.nytapp.dataManager.NYTBluePrint
import com.example.nytapp.dataManager.NYTLocalHandler
import com.example.nytapp.dataManager.NYTRemoteHandler
import com.example.nytapp.dataManager.NYTRepository
import com.example.nytapp.listModule.ui.TopStoriesFragment
import com.example.nytapp.listModule.viewModel.ContainerViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@ContainerScope
@Component(dependencies = [BaseComponent::class], modules = [ContainerModule::class])
interface ContainerComponent {
    fun inject(containerViewModel: ContainerViewModel)
    fun inject(topStoriesFragment: TopStoriesFragment)
}

@ContainerScope
@Module
class ContainerModule {
    @ContainerScope
    @Provides
    fun getAdapter() = BaseRecyclerAdapter()

    @ContainerScope
    @Provides
    fun getListRepo(local: NYTBluePrint.Local,
                    remote: NYTBluePrint.Remote,
                    compositeDisposable: CompositeDisposable): NYTRepository = NYTRepository(local, remote, compositeDisposable)

    @ContainerScope
    @Provides
    fun getLocal(appDB: AppDB): NYTBluePrint.Local = NYTLocalHandler(appDB)

    @ContainerScope
    @Provides
    fun getRemote(postService: PostService): NYTBluePrint.Remote = NYTRemoteHandler(postService)

    @ContainerScope
    @Provides
    fun getCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    /* Base provides dependencies */
    @ContainerScope
    @Provides
    fun appDB(context: Context): AppDB = Room.databaseBuilder(context, AppDB::class.java, "app.db").build()

    @ContainerScope
    @Provides
    fun getPostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}