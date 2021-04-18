package com.shkiper.foodapp.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideOrderRepo(appDatabase: AppDatabase): OrderRepoI {
        return OrderRepo(appDatabase)
    }
}