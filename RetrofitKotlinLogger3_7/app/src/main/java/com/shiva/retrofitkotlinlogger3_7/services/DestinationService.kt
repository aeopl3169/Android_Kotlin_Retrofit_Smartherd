package com.shiva.retrofitkotlinlogger3_7.services

import com.shiva.retrofitkotlinlogger3_7.models.Destination
import retrofit2.Call
import retrofit2.http.GET

// The functions in DestinationService interface will map to the web service(server) end point URL's
interface DestinationService {
    // Here the @GET annotation is used because the function in the server will accept get request(line no.50 in server)
    // And same endpoint "destination" should be same as defined in the server(line no.50 in server).
    @GET("destination")
    // this function will return Call object (which will fetch list (destination) from the server)
    fun getDestinationList(): Call<List<Destination>>
}