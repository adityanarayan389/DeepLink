package com.iserveu.paymentstatus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Status : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        val uri = intent.data
        if (uri != null){
            val path= uri.toString()
        }
    }
}