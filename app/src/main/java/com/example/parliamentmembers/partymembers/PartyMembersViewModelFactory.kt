package com.example.parliamentmembers.partymembers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model factory for the party members view model

class PartyMembersViewModelFactory(
        private val repository: Repository, private val partyName: String) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PartyMembersViewModel::class.java)){
            return PartyMembersViewModel(repository, partyName) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}