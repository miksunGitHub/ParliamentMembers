package com.example.parliamentmembers.databaseAndNetwork

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ParliamentMemberDao {
    @Insert
    suspend fun insert(entry: ParliamentMember)
    @Query("select * from member_data")
    fun getAll(): LiveData<List<ParliamentMember>>
    @Query("select party from member_data")
    fun getParties(): LiveData<List<String>>
}