package com.rahulografy.yapodyt.di

import com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource.YouTubeVideosRemoteDataSource
import com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource.YouTubeVideosRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindYouTubeVideosRemoteDataSource(
        youTubeVideosRemoteDataSourceImpl: YouTubeVideosRemoteDataSourceImpl
    ): YouTubeVideosRemoteDataSource = youTubeVideosRemoteDataSourceImpl
}
