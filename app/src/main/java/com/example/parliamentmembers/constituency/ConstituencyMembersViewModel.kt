package com.example.parliamentmembers.constituency

import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.Repository


//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//ViewModel passes live data from repository to the fragment class.

class ConstituencyMembersViewModel(repository: Repository, constituency: String): ViewModel() {

    val membersByConstituency=repository.getMembersByConstituency(constituency)

}