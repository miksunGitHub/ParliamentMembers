package com.example.parliamentmembers.partymembers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

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