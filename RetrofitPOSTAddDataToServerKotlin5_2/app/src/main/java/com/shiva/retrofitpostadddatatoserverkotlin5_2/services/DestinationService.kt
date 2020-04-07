package com.shiva.retrofitpostadddatatoserverkotlin5_2.services

import com.shiva.retrofitpostadddatatoserverkotlin5_2.models.Destination
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

// The functions in DestinationService interface will map to the web service(server) end point URL's
interface DestinationService {
    // Here the @GET annotation is used because the function in the server will accept get request(line no.50 in server)
    // And same endpoint "destination" should be same as defined in the server(line no.50 in server).
//    @GET("destination")
    // this function will return Call object (which will fetch list (destination) from the server)
//    fun getDestinationList(): Call<List<Destination>>

    // Query parameter
//    @GET("destination")
    // Annotating with the @Query to tell retrofit about the id which is passed on runtime.(line no.52 in server)
//    fun getDestinationList(@Query("country") country: String?): Call<List<Destination>>

    // Double Query parameter
//    @GET("destination")
    // Annotating with the @Query to tell retrofit about the query id which is passed on runtime.(line no.51 in server)
//    fun getDestinationList(@Query("country") country: String?, @Query("count") count: String?): Call<List<Destination>>

    // Query map
    @GET("destination")
    // Annotating with the @QueryMap to tell retrofit about the query id which is passed on runtime.(line no.51 in server)
    fun getDestinationList(@QueryMap filter: HashMap<String, String>): Call<List<Destination>>

    // Path parameter
    // The id inside curly braces will be replaced with selected id (i.e. http://base-url/destination/2)
    @GET("destination/{id}")
    // Annotating with the @Path to tell retrofit about the id which is passed on runtime.(line no.63 in server)
    fun getDestination(@Path("id") id: Int): Call<Destination>

    // Adding
    @POST("destination") // destination endpoint server line no. 72
    fun addDestination(@Body newDestination: Destination) : Call<Destination>
}