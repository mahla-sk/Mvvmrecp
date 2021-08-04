package com.example.mvvmrecp.di

import android.content.Context
import com.example.mvvmrecp.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providApplication(@ApplicationContext app:Context):BaseApplication{
        return app as BaseApplication
    }

//    @Singleton
//    @Provides
//    fun randomstring():String{
//        return "hey u did it well!!"
//    }
}