package com.dodemy.retrofitpostrequest.activities

import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dodemy.retrofitpostrequest.R
import com.dodemy.retrofitpostrequest.services.MessageService
import com.dodemy.retrofitpostrequest.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // To be replaced by retrofit code
        //Use 10.0.2.2 if you are running your app in emulator
        //9000 used here is the server port from NodeJS Express: server.js
        //Use your laptop Wifi IP address if you are using real phone device instead of emulator ie 192.168.0.100.
        //messages used here is a route from your server after port 9000 ie :9000/messages
        val messageService = ServiceBuilder.buildService(MessageService::class.java)
        val requestCall = messageService.getMessages("http://10.0.2.2:9000/messages")

        requestCall.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val msg = response.body()
                    msg?.let {
                        message.text = msg
                    }
                } else {
                    Toast.makeText(
                        this@WelcomeActivity,
                        "Failed to retrieve items", Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(
                    this@WelcomeActivity,
                    "Failed to retrieve items", Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    fun getStarted(view: View) {
        val intent = Intent(this, DestinationListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
