package com.example.parliamentmembers

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembers.databaseAndNetwork.*
import kotlinx.coroutines.launch

class PartyViewModel(
        val database: ParliamentMemberDao,
        application: Application): AndroidViewModel(application) {

        private val _parliamentMemberData= MutableLiveData<List<ParliamentMemberJsonData>>()
        val parliamentMemberData: LiveData<List<ParliamentMemberJsonData>>
                get()=_parliamentMemberData

        fun onClick(){
                getData()
                saveData()
        }

        fun getData(){
                viewModelScope.launch{
                        try{
                                _parliamentMemberData.value= ParliamentApi.retrofitService.getProperties()
                                Log.d("#####","ok")

                        } catch(e: Exception){

                                _parliamentMemberData.value=ArrayList()
                                Log.d("#####", e.toString())
                        }
                }


        }

        fun saveData(){
                _parliamentMemberData.value?.forEach{
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


}