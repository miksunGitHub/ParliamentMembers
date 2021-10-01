package com.example.parliamentmembers

import android.util.Log
import androidx.lifecycle.*
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMemberJsonData
import com.example.parliamentmembers.databaseAndNetwork.Repository


class PartyMembersViewModel (private val repository: Repository, private val partyName: String): ViewModel(){

    val parliamentMemberDataByParty=Transformations.map(repository.getAll()){ it.filter{it.party==partyName} }
}