package com.shiva.retrofitkotlin4_3.services

import com.shiva.retrofitkotlin4_3.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// The functions in DestinationService interface will map to the web service(server) end point URL's
interface DestinationService {
    // Here the @GET annotation is used because the function in the server will accept get request(line no.50 in server)
    // And same endpoint "destination" should be same as defined in the server(line no.50 in server).
    @GET("destination")
    // this function will return Call object (which will fetch list (destination) from the server)
    fun getDestinationList(): Call<List<Destination>>

    // The id inside curly braces will be replaced with selected id (i.e. http://base-url/destination/2)
    @GET("destination/{id}")
    // Annotating with the @Path to tell retrofit about the id which is passed on runtime.
    fun getDestination(@Path("id") id: Int): Call<Destination>
}