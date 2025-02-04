package com.shiva.retrofitkotlin3_5.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shiva.retrofitkotlin3_5.models.Destination
import com.shiva.retrofitkotlin3_5.services.DestinationService
import com.shiva.retrofitkotlin3_5.services.ServiceBuilder
import com.shiva.retrofitkotlin3_5.R
import kotlinx.android.synthetic.main.activity_destiny_create.*

class DestinationCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_create)

        setSupportActionBar(toolbar)
        val context = this

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = et_city.text.toString()
            newDestination.description = et_description.text.toString()
            newDestination.country = et_country.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
//            val requestCall = destinationService.addDestination(newDestination)

//            requestCall.enqueue(object: Callback<Destination> {
//
//                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
//                    if (response.isSuccessful) {
//                        finish() // Move back to DestinationListActivity
//                        var newlyCreatedDestination = response.body() // Use it or ignore it
//                        Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<Destination>, t: Throwable) {
//                    Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
//                }
//            })
        }
    }
}
