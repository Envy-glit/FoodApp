package com.shkiper.foodapp.di

import com.shkiper.foodapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun mainActivityProvider(): MainActivity

//    @ContributesAndroidInjector
//    abstract fun cartActivityProvider(): CartActivity

}