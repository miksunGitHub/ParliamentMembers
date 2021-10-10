package com.example.parliamentmembers.databaseAndNetwork

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//The structure of the Json object, that is fetched from an outside source

data class ParliamentMemberJsonData(
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String,
    val bornYear: Int,
    val constituency: String,
)