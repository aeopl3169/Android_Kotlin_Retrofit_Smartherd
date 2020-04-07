package com.shiva.retrofitkotlinlogger3_7.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shiva.retrofitkotlinlogger3_7.helpers.DestinationAdapter
import com.shiva.retrofitkotlinlogger3_7.models.Destination
import com.shiva.retrofitkotlinlogger3_7.services.DestinationService
import com.shiva.retrofitkotlinlogger3_7.services.ServiceBuilder
import com.shiva.retrofitkotlinlogger3_7.R
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
        val requestCall = destinationService.getDestinationList()
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
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                }
            }
        })
    }
}