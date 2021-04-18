package com.shkiper.foodapp.di

import com.shkiper.foodapp.repo.OrderRepo
import com.shkiper.foodapp.repo.OrderRepoI
import com.shkiper.foodapp.room.database.AppDatabase
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