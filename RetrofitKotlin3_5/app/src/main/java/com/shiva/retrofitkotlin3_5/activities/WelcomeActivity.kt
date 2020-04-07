package com.shiva.retrofitkotlin3_5.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shiva.retrofitkotlin3_5.services.MessageService
import com.shiva.retrofitkotlin3_5.services.ServiceBuilder
import com.shiva.retrofitkotlin3_5.R
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // To be replaced by retrofit code

        val messageService = ServiceBuilder.buildService(MessageService::class.java)
        Log.d("Shiva","messageService: "+messageService.toString())
        val requestCall = messageService.getMessages("http://10.0.2.2:9000/messages") // server line no.45
//        val requestCall = messageService.getMessages("http://192.168.0.10:9000/messages/")
//        val requestCall = messageService.getMessages("http://127.0.0.1:9000/messages/")
        Log.d("Shiva","requestCall: "+requestCall.toString())

        requestCall.enqueue(object: Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("Shiva","response: "+response)
                if (response.isSuccessful) {
                    val msg = response.body()
                    Log.d("Shiva","response.body(): "+msg) // server line no.45
                    msg?.let {
                        message.text = msg
                    }
                } else {
                    Toast.makeText(this@WelcomeActivity,
                        "Failed to retrieve items", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@WelcomeActivity,
                    "Failed to connect ", Toast.LENGTH_LONG).show()
                Log.e("Shiva","Throwable: "+t)
            }

        })
    }

    fun getStarted(view: View) {
        val intent = Intent(this, DestinationListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
