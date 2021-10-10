package com.example.parliamentmembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.parliamentmembers.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//The main activity creates the navigation drawer for the app.

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,
                R.layout.activity_main
        )

        var drawerLayout=binding.drawerLayout

        val navController=this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(this.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)

        return NavigationUI.navigateUp(navController, drawerLayout)
    }




}