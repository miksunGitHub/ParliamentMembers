package com.example.parliamentmembers.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.Transformation
import com.example.parliamentmembers.Functions
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for the member fragment. Passes an live data object that matches the id given in the
//constructor with the observed live data in the repository.

class MemberViewModel(repository: Repository, memberID: Int): ViewModel(),
        Functions {

    val getMemberById=repository.getMemberByID(memberID.toLong())


}