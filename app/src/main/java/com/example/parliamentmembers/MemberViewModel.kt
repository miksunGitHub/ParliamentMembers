package com.example.parliamentmembers

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.Repository

class MemberViewModel(private val repository: Repository, private val memberID: Int): ViewModel() {

    val parliamentMemberDataByID=Transformations.map(repository.getAll()){ it.filter{it.id==memberID} }
}