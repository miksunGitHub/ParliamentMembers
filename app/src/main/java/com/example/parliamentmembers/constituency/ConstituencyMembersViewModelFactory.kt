package com.example.parliamentmembers.constituency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model factory for the constituency view model

class ConstituencyMembersViewModelFactory (
    private val repository: Repository, private val constituency: String
    ) : ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ConstituencyMembersViewModel::class.java)){
                return ConstituencyMembersViewModel(repository, constituency) as T

            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }