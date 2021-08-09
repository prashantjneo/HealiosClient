package com.healios.io.assignment.network

import com.google.gson.GsonBuilder
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APINetwork {

    companion object {

        //Build Retrofit Client
        fun getClient(baseURL: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(getUnsafeOkHttpClient().build())
                .build()
        }

        private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {

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
    }

}