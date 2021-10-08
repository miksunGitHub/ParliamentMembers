package com.example.parliamentmembers.rating

import androidx.lifecycle.ViewModel

import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.Repository

class RatingViewModel(private val repository: Repository, memberId: Int): ViewModel() {

    fun getMemberByID(id: Long)=repository.getMemberByID(id)

    fun updateMember(parliamentMember: ParliamentMember)=repository.updateMember(parliamentMember)


}