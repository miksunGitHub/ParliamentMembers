package com.example.parliamentmembers.name

import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.Repository

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for the name fragment. Gets the name as constructor parameter, passes that to
//repository function to find the matching view model object.

class NameViewModel(repository: Repository, private val first: String, private val last: String): ViewModel(){

        var member=repository.getMemberByName(first, last)
}