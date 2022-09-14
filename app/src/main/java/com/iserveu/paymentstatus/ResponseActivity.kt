package com.iserveu.paymentstatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ResponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)
        val data = intent
        val datastring = intent.action
        val uri = intent.data
        Log.d("intent_data",uri.toString())
    }
}