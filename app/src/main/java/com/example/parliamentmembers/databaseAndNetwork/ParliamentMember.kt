package com.example.parliamentmembers.databaseAndNetwork

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="member_data")
data class ParliamentMember(
    @PrimaryKey(autoGenerate=true)
    var id: Int=0,
    var first_name: String,
    var last_name: String,
    var age: Int,
    var party: String,
    var constituency: String,
    var minister: Boolean,
    var rating: Int,
    var review: String
)