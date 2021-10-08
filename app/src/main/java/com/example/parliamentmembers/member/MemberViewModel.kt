package com.example.parliamentmembers.member



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.Functions
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.Repository

class MemberViewModel(private val repository: Repository, private val memberID: Int): ViewModel(), Functions {

    private val _member= MutableLiveData<ParliamentMember>()
    val member: LiveData<ParliamentMember>
        get()=_member

    private val _firstName= MutableLiveData<String>()
    val firstName: LiveData<String>
        get()=_firstName




    var getMemberById=repository.getMemberByID(memberID.toLong())

}