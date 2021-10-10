package com.example.parliamentmembers.party

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model factory for the party view model

class PartyViewModelFactory(
    private val repository: Repository) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PartyViewModel::class.java)){
            return PartyViewModel(repository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}