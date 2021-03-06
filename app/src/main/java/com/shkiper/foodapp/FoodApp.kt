package com.shkiper.foodapp

import android.util.Log
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import com.shkiper.foodapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class FoodApp: DaggerApplication(){


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        Log.d(TAG, " >>> FoodApplication Created")

        if (BuildConfig.DEBUG) {
            Log.d(TAG, " >>> Initializing Stetho")
            Stetho.initializeWithDefaults(this)
        }

        return DaggerAppComponent.builder().application(this).build()
    }

    companion object {
        const val TAG = "FoodApplication"
    }



}