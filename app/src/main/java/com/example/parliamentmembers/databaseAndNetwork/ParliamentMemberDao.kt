package com.example.parliamentmembers.databaseAndNetwork

import androidx.lifecycle.LiveData
import androidx.room.*

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//DAO functions to get data from the database and to modify it
//
//https://stackoverflow.com/questions/49829672/how-to-avoid-duplicate-data-entry-in-a-table-in-room-db/49830013

@Dao
interface ParliamentMemberDao {

    //Adds an entry to the database. If matching entry exists, ignores it.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: ParliamentMember)

    //Updates an existing entry in the database
    @Update
    suspend fun updateMember(parliamentMember: ParliamentMember)

    //Gets all the entries in the database
    @Query("select * from member_data")
    fun getAll(): LiveData<List<ParliamentMember>>

    //Gets all the party names stored in the database entries
    @Query("select party from member_data")
    fun getParties(): LiveData<List<String>>

    //Gets all the entries that match with the party name given
    @Query("select * from member_data where party=:party")
    fun getMembersByParty(party: String): LiveData<List<ParliamentMember>>

    //Gets an entry that matchies with the id given
    @Query("select * from member_data where id=:id")
    fun getMemberByID(id: Long): LiveData<ParliamentMember>

    //Gets an entry that matches with the first and last name given
    @Query("select * from member_data where first_name=:first and last_name=:last")
    fun getMemberByName(first: String, last: String): LiveData<ParliamentMember>

    //Gets all the entries that match with the constituency given
    @Query("select * from member_data where constituency=:constituency")
    fun getMembersByConstituency(constituency: String): LiveData<List<ParliamentMember>>
}