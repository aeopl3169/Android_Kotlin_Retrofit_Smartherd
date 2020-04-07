package com.shiva.retrofitpostadddatatoserverkotlin5_2.services

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "http://10.0.2.2:9000/"
//private const val URL = "http://192.168.0.10/9000/"
//private const val URL = "http://127.0.0.1:9000/" // Configure in the server line no.115

    // Static instance of HttpLoggingInterceptor class (creating logger) || implementation 'com.squareup.okhttp3:logging-interceptor:4.4.0'
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    // Creating OkHttp Client with HttpLoggingInterceptor
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    // Creating Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // Creating Retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        Log.d("Shiva","serviceType: "+serviceType)
        return retrofit.create(serviceType)
    }
}