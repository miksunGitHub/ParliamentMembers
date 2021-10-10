package com.example.parliamentmembers.databaseAndNetwork

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Defines the entry stored in the database

@Entity(tableName="member_data", indices=[Index(value=["member_number"], unique=true)])
data class ParliamentMember(
    @PrimaryKey(autoGenerate=true)
    var id: Int=0,
    @ColumnInfo(name="member_number")
    var member_num: Int,
    var first_name: String,
    var last_name: String,
    var age: Int,
    var party: String,
    var constituency: String,
    var minister: Boolean,
    var rating: Float,
    var review: String,
    var picture: String
    )