package com.shiva.retrofitkotlinquerymap4_5.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shiva.retrofitkotlinquerymap4_5.helpers.DestinationAdapter
import com.shiva.retrofitkotlinquerymap4_5.models.Destination
import com.shiva.retrofitkotlinquerymap4_5.services.DestinationService
import com.shiva.retrofitkotlinquerymap4_5.services.ServiceBuilder
import com.shiva.retrofitkotlinquerymap4_5.R
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_list)

        setSupportActionBar(idToolbar)
        idToolbar.title = title
        Log.d("Shiva","Title: "+title)

        idFloatingActionButton.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadDestinations()
    }

    private fun loadDestinations() {
        // To be replaced by retrofit code
//        destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATION)

        // Reference of the interface (DestinationService)
        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        // Using the interface object, we are calling the required functions
//        val requestCall = destinationService.getDestinationList()
        // If we pass null in the parameter Retrofit will ignore and display all the items.
//        val requestCall = destinationService.getDestinationList("India", "1")

        // Query Map
        val filter = HashMap<String, String>()
        filter["country"] = "India"
        filter.put("count", "1")
        val requestCall = destinationService.getDestinationList(filter)
        requestCall.enqueue(object: Callback<List<Destination>>{
            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Log.e("Shiva", "Error: "+t)
            }

            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>
            ) {
                Log.i("Shiva", " DestinationListActivity response: "+response)
                Log.i("Shiva", " DestinationListActivity call: "+call)
                if (response.isSuccessful){
                    val destinationList = response.body()!!
                    Log.i("Shiva", " DestinationListActivity response.body: "+destinationList)
                    // attaching the list to recycler view adapter.
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                }
            }
        })
    }
}