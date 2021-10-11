package com.example.parliamentmembers.rating

import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for rating fragment. Delivers the functions to check the previously saved ratings and
//to save the newly created data.

class RatingViewModel(val repository: Repository, val memberId: Int): ViewModel() {

    fun getMemberByID()=repository.getMemberByID(memberId.toLong())

    fun updateMember(parliamentMember: ParliamentMember)=repository.updateMember(parliamentMember)
}