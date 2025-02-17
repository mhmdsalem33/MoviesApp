package com.salem.moviesapp.data.di

import com.salem.moviesapp.data.data_source.remote.api.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    init {
        System.loadLibrary("native-lib")
    }
    private external fun getEncryptedBaseUrl(): String
    private external fun getEncryptedToken(): String


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor( ) =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .apply {
                        header("Accept"          ,  "application/json")
                        header("Content-Type"    ,  "application/json")
                        header("Accept-Language" ,  "en")
                        header("Authorization"   ,    getEncryptedToken())
                    }
                    .build()
            )
        }

    @Provides
    @Singleton
    fun provideOkhttp(logging: HttpLoggingInterceptor, headersInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(20 , TimeUnit.SECONDS)
            .readTimeout(   20 , TimeUnit.SECONDS)
            .addInterceptor( logging )
            .addInterceptor( headersInterceptor )
            .build()

    @Provides
    @Singleton
    fun provideApi( okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(getEncryptedBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMoviesService(retrofit: Retrofit) : MoviesApi =
        retrofit.create(MoviesApi::class.java)

}