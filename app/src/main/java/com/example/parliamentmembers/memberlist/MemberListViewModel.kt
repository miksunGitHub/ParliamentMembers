package com.example.parliamentmembers.memberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for the member fragment. Delivers list of live data objects from the repository.

class MemberListViewModel(repository: Repository): ViewModel(){

    private val _members= MutableLiveData<List<ParliamentMember?>>()
    val members: LiveData<List<ParliamentMember?>>
        get()=_members


    //Observes the method in repository, that shows all the parliament members in the database.
    //Sorts the objects by the last name of the member.
    var parliamentMemberData= Transformations.map(repository.getAll()){ list ->
        list.distinct().sortedBy { it.last_name }
    }



}