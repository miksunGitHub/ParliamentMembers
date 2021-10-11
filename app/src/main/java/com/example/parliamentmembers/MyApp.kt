package com.example.parliamentmembers

import android.app.Application
import android.content.Context

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Class copied from materials provided by Metropolia University of Applied Sciences

class MyApp: Application() {
    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate(){
        super.onCreate()
        appContext =applicationContext
    }
}