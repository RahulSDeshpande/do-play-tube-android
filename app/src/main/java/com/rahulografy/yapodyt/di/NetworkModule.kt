package com.rahulografy.yapodyt.di

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rahulografy.yapodyt.util.Constants.Network.Api.URL_BASE
import com.rahulografy.yapodyt.util.Constants.Network.Cache.NAME
import com.rahulografy.yapodyt.util.Constants.Network.Timeout.CONNECTION
import com.rahulografy.yapodyt.util.Constants.Network.Timeout.READ
import com.rahulografy.yapodyt.util.Constants.Network.Timeout.WRITE
import com.rahulografy.yapodyt.util.Memory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun buildOkHttpClient(application: Application): OkHttpClient =
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(BODY))
            .connectTimeout(CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(WRITE, TimeUnit.SECONDS)
            .readTimeout(READ, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(application.cacheDir, NAME),
                    Memory.calculateCacheSize(context = application, size = .25f)
                )
            )
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient = buildOkHttpClient(application)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}
