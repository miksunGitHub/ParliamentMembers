package com.example.parliamentmembers.databaseAndNetwork

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class Repository private constructor(private val parliamentMemberDao: ParliamentMemberDao){

    fun getParties()=parliamentMemberDao.getParties()



    companion object{
        @Volatile private var instance: Repository?=null

        fun getInstance(parliamentMemberDao: ParliamentMemberDao)=
            instance?: synchronized(this){

                instance?: Repository(parliamentMemberDao).also{ instance=it}
            }
    }

    fun getAll()=parliamentMemberDao.getAll()



    fun fetchData(viewModelScope: CoroutineScope): MutableLiveData<List<ParliamentMemberJsonData>>{

        var parliamentMemberData=MutableLiveData<List<ParliamentMemberJsonData>>()

        viewModelScope.launch{
            try{
                parliamentMemberData.value= ParliamentApi.retrofitService.getProperties()
                Log.d("#####","ok")

            } catch(e: Exception){

                parliamentMemberData.value=ArrayList()
                Log.d("#####", e.toString())
            }

            parliamentMemberData.value?.forEach{
                var firstName=it.first
                var lastName=it.last
                var age=2020-it.bornYear
                var constituency=it.constituency
                var party=it.party
                var minister=it.minister

                var parliamentMember= ParliamentMember(first_name = firstName, last_name = lastName, age = age, party = party, constituency = constituency, minister = minister, rating = 0, review = "")

                viewModelScope.launch {
                    ParliamentMemberDataBase.getInstance().parliamentMemberDao.insert(parliamentMember)
                }
            }
    }
        return parliamentMemberData
    }
}



