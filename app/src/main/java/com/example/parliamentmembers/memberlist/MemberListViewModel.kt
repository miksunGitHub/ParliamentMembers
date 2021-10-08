package com.example.parliamentmembers.memberlist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.Repository

class MemberListViewModel(private val repository: Repository): ViewModel(){

    val parliamentMemberData= Transformations.map(repository.getAll()){
        it.distinct().sortedBy { it.last_name }
    }
}