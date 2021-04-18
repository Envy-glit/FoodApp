package com.shkiper.foodapp.di

import android.app.Application
import com.shkiper.foodapp.FoodApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    RoomModule::class,
    RepoModule::class
])
interface AppComponent: AndroidInjector<FoodApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}