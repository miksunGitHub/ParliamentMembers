package com.example.parliamentmembers.utilities

import com.example.parliamentmembers.PartyViewModelFactory
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMemberDataBase
import com.example.parliamentmembers.databaseAndNetwork.Repository

object InjectorUtils {
    fun partyViewModelFactory(): PartyViewModelFactory {

        val repository= Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return PartyViewModelFactory(repository)
    }

}