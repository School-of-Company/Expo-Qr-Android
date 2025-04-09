package com.school_of_company.expoqrandroid.network

import android.util.Log
import com.school_of_company.expoqrandroid.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor { message -> Log.d("HTTP_LOG", message) }
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.QR_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
