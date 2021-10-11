package com.example.parliamentmembers.databaseAndNetwork

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Repository manages the functions needed to get data from an outside source, create a database to
//store it and to get data from the database.

class Repository private constructor(private val parliamentMemberDao: ParliamentMemberDao) {

    private val _parliamentMemberData=MutableLiveData<List<ParliamentMemberJsonData>>()
    private val parliamentMemberData: LiveData<List<ParliamentMemberJsonData>>
        get()=_parliamentMemberData

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(parliamentMemberDao: ParliamentMemberDao) =
                instance ?: synchronized(this) {

                    instance ?: Repository(parliamentMemberDao).also { instance = it }
                }
    }

    //Fetches json data from an outside source
    fun fetchData(viewModelScope: CoroutineScope) {

        //val parliamentMemberData = MutableLiveData<List<ParliamentMemberJsonData>>()

        viewModelScope.launch {
            try {
                _parliamentMemberData.value = ParliamentApi.retrofitService.getProperties()

            } catch (e: Exception) {

                _parliamentMemberData.value = ArrayList()
                Log.d("¤¤¤¤¤¤¤¤¤¤", e.toString())
            }
            saveData(parliamentMemberData, viewModelScope)
        }
    }

    //Gets a json object, saves the wanted data from it to a parliament member object. Adds the
    //object to the database.
    private fun saveData(data: LiveData<List<ParliamentMemberJsonData>>, viewModelScope: CoroutineScope) {

        data.value?.forEach {
            val personNumber = it.personNumber
            val firstName = it.first
            val lastName = it.last
            val age = 2020 - it.bornYear
            val constituency = it.constituency
            val party = it.party
            val minister = it.minister
            val picture = it.picture

            val parliamentMember = ParliamentMember(member_num = personNumber, first_name = firstName, last_name = lastName, age = age, party = party, constituency = constituency, minister = minister, rating = 0f, review = "", picture = picture)

            viewModelScope.launch {
                try {
                    ParliamentMemberDataBase.getInstance().parliamentMemberDao.insert(parliamentMember)

                } catch (e: Exception) {
                    Log.d("¤¤¤¤¤¤¤¤¤¤", e.toString())
                }
            }
        }
    }

    //Get all entries
    fun getAll() = parliamentMemberDao.getAll()

    //Get all the parties
    fun getParties() = parliamentMemberDao.getParties()

    //Get an entry matching the given id
    fun getMemberByID(id: Long) = parliamentMemberDao.getMemberByID(id)

    //Get all entries matching the party given
    fun getMembersByParty(party: String)=parliamentMemberDao.getMembersByParty(party)

    //Get all entries matching the constituency given
    fun getMembersByConstituency(constituency: String) = parliamentMemberDao.getMembersByConstituency(constituency)

    //Get an entry, that matches with the first and last name given
    fun getMemberByName(first: String, last: String)=parliamentMemberDao.getMemberByName(first, last)

    //Update an existing entry
    fun updateMember(parliamentMember: ParliamentMember) {

        GlobalScope.launch {
            try {
                parliamentMemberDao.updateMember(parliamentMember)
            } catch (e: Exception) {
                Log.d("¤¤¤¤¤¤¤¤¤¤", e.toString())
            }
        }
    }
}




