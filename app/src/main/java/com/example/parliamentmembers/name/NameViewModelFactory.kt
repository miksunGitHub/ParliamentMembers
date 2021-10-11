package com.example.parliamentmembers.name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model factory for the name view model

class NameViewModelFactory (
    private val repository: Repository, private val firstName: String, private val lastName: String
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NameViewModel::class.java)){
            return NameViewModel(repository, firstName, lastName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}