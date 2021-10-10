package com.example.parliamentmembers.member

import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.Functions
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for the member fragment. Passes an live data object that matches the id given in the
//constructor with the observed live data in the repository.

class MemberViewModel(private val repository: Repository, private val memberID: Int): ViewModel(),
        Functions {

    var getMemberById=repository.getMemberByID(memberID.toLong())

}