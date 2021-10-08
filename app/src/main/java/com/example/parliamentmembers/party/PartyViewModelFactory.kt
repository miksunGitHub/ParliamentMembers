package com.example.parliamentmembers.party

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

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