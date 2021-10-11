package com.example.parliamentmembers.memberlist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for the member fragment. Delivers list of live data objects from the repository.

class MemberListViewModel(private val repository: Repository): ViewModel(){

    //Observes the method in repository, that shows all the parliament members in the database.
    //Sorts the objects by the last name of the member.
    var parliamentMemberData= Transformations.map(repository.getAll()){ list ->
        list.distinct().sortedBy { it.last_name }
    }
}