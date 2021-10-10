package com.example.parliamentmembers.partymembers

import androidx.lifecycle.*

import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for the party members fragment. Gets the party name as a constructor parameter,
//delivers the live data of parliament member objects from the repository.

class PartyMembersViewModel (private val repository: Repository, private val partyName: String): ViewModel(){

    //Observes the live data list of parliament member objects, that matches the party name given
    //in the class constructor.
    //Sorts the list in to an alphabetical order by last name.
    val parliamentMemberDataByParty=Transformations.map(repository.getMembersByParty(partyName)){
        it.sortedBy { it.last_name }
    }

}