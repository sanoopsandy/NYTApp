package com.example.nytapp.core.di

import com.example.nytapp.base.BaseApplication
import com.example.nytapp.listModule.di.ContainerComponent
import com.example.nytapp.listModule.di.DaggerContainerComponent
import javax.inject.Singleton

@Singleton
object DIHandler {

    private var containerComponent: ContainerComponent? = null

    /*
    * Fetch the component dependency
    * */
    fun getContainerComponent(): ContainerComponent {
        if (containerComponent == null) {
            containerComponent = DaggerContainerComponent.builder().baseComponent(BaseApplication.baseComponent).build()
        }
        return containerComponent as ContainerComponent
    }

    fun destroyContainerComponent() {
        containerComponent = null
    }

}
