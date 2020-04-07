package com.shiva.retrofit_kotlin_3_2.services

import com.shiva.retrofit_kotlin_3_2.models.Destination
import retrofit2.Call
import retrofit2.http.GET

// The functions in DestinationService interface will map to the web service(server) end point URL's
interface DestinationService {
    // Here the @GET annotation is used because the function in the server will accept get request(line no.50 in server)
    // And same endpoint "destination" should be same as defined in the server(line no.50 in server).
    @GET("destination")
    fun getDestinationList(): Call<List<Destination>>
}