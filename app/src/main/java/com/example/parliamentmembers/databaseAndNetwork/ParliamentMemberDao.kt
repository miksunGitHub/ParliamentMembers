package com.example.parliamentmembers.databaseAndNetwork

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ParliamentMemberDao {
    @Insert
    suspend fun insert(entry: ParliamentMember)
    @Update
    suspend fun updateMember(parliamentMember: ParliamentMember)
    @Query("select * from member_data")
    fun getAll(): LiveData<List<ParliamentMember>>
    @Query("select party from member_data")
    fun getParties(): LiveData<List<String>>
    @Query("select * from member_data where id=:id1")
    fun getMemberByID(id1: Long): LiveData<List<ParliamentMember>>
}