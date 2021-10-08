package com.example.parliamentmembers.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

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