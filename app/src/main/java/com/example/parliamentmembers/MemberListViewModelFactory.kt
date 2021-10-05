package com.example.parliamentmembers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

class MemberListViewModelFactory(
        private val repository: Repository) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MemberListViewModel::class.java)){
            return MemberListViewModel(repository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
