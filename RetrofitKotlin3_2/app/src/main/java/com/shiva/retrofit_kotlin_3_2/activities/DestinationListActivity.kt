package com.shiva.retrofit_kotlin_3_2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shiva.retrofit_kotlin_3_2.R
import com.shiva.retrofit_kotlin_3_2.helpers.DestinationAdapter
import com.shiva.retrofit_kotlin_3_2.models.Destination
import com.shiva.retrofit_kotlin_3_2.services.DestinationService
import com.shiva.retrofit_kotlin_3_2.services.ServiceBuilder
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

        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall = destinationService.getDestinationList()
        requestCall.enqueue(object: Callback<List<Destination>>{
            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>
            ) {
                if (response.isSuccessful){
                    val destinationList = response.body()!!
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                }
            }
        })
    }
}