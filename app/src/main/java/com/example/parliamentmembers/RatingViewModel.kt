package com.example.parliamentmembers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.Repository

class RatingViewModel(private val repository: Repository, memberId: Long): ViewModel() {

    fun getMemberByID(id: Long)=repository.getMemberByID(id)

    fun updateMember(parliamentMember: ParliamentMember)=repository.updateMember(parliamentMember, viewModelScope)


}