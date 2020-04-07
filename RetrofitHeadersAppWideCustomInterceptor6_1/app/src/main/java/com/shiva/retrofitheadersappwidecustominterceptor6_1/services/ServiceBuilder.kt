package com.shiva.retrofitheadersappwidecustominterceptor6_1.services

import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private const val URL = "http://10.0.2.2:9000/"
//private const val URL = "http://192.168.0.10/9000/"
//private const val URL = "http://127.0.0.1:9000/" // Configure in the server line no.115

    // Static instance of HttpLoggingInterceptor class (creating logger) || implementation 'com.squareup.okhttp3:logging-interceptor:4.4.0'
    //
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // By adding Interceptor Headers we are adding "x-device-type: generic_x86" and "Accept-Language: en" will be sent to server.
    // Creating Interceptor for applying to Headers application wide
    val headerInterceptor = object: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .build()
            val response = chain.proceed(request)
            return response
        }
    }

    // Default timeout of Retrofit is 10 seconds 11th second onFailure(ServerTimeOut Exception) will be executed, to prevent that we use
    // .connectTimeout(19,TimeUnit.SECONDS) -> Retrofit waits to establish a network connection to the server
    // .readTimeout(13,TimeUnit.SECONDS) -> Retrofit will wait to receive data, once the connection is established to server
    // .writeTimeout(16,TimeUnit.SECONDS) -> Retrofit will wait till the device write to the server, once the connection is established to server
    // .callTimeout(15, TimeUnit.SECONDS) -> Retrofit waits for the entire network operation.
    // Creating OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        .callTimeout(15, TimeUnit.SECONDS) // Default timeout of Retrofit is 10 seconds
        .addInterceptor(headerInterceptor) // HeaderInterceptor should add before HttpLoggingInterceptor
        .addInterceptor(logger) // Adding HttpLoggingInterceptor
    // Creating Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create()) //'com.squareup.retrofit2:converter-gson:2.7.1'
        .client(okHttp.build())

    // Creating Retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        Log.d("Shiva","serviceType: "+serviceType)
        return retrofit.create(serviceType)
    }
}