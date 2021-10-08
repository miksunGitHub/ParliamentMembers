package com.example.parliamentmembers.partymembers


import androidx.lifecycle.*

import com.example.parliamentmembers.databaseAndNetwork.Repository


class PartyMembersViewModel (private val repository: Repository, private val partyName: String): ViewModel(){

    val parliamentMemberDataByParty=Transformations.map(repository.getAll()){ it.filter{it.party==partyName}.sortedBy { it.last_name } }
}