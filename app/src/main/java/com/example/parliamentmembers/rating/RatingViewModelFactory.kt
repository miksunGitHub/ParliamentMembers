package com.example.parliamentmembers.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model factory for the rating view model

class RatingViewModelFactory (
    private val repository: Repository, private val memberID: Int
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RatingViewModel::class.java)){
            return RatingViewModel(repository, memberID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}