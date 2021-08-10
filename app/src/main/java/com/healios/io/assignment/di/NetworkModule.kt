package com.healios.io.assignment.di

import com.google.gson.GsonBuilder
import com.healios.io.assignment.network.APIInterface
import com.healios.io.assignment.utils.Constants
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseURL() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient.Builder {
        try {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(50, TimeUnit.MINUTES)
            builder.readTimeout(50, TimeUnit.SECONDS)
            builder.writeTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(OkHttpProfilerInterceptor())
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient.Builder, baseURL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient.build())
            .build()


    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit):APIInterface= retrofit.create(APIInterface::class.java)

}