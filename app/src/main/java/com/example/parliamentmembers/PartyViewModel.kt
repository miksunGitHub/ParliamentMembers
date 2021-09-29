package com.example.parliamentmembers

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.parliamentmembers.databaseAndNetwork.*
import kotlinx.coroutines.launch

class PartyViewModel(private val repository: Repository): ViewModel() {

        private val _parliamentMemberData= MutableLiveData<List<ParliamentMemberJsonData>>()
        val parliamentMemberData: LiveData<List<ParliamentMemberJsonData>>
                get()=_parliamentMemberData


        init{
                _parliamentMemberData.value=repository.getData(viewModelScope).value
        }

        fun getParties()=repository.getParties()

}