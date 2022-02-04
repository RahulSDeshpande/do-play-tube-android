package com.rahulografy.yapodyt.di

import com.rahulografy.yapodyt.data.source.remote.youtune.videos.service.YoutubeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideYoutubeApiService(retrofit: Retrofit): YoutubeApiService =
        retrofit.create(YoutubeApiService::class.java)
}
