package com.example.parliamentmembers.databaseAndNetwork

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey



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