package com.example.parliamentmembers.utilities

import com.example.parliamentmembers.MemberViewModelFactory
import com.example.parliamentmembers.PartyMembersFragmentArgs
import com.example.parliamentmembers.PartyMembersViewModelFactory
import com.example.parliamentmembers.PartyViewModelFactory
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMemberDataBase
import com.example.parliamentmembers.databaseAndNetwork.Repository

object InjectorUtils {
    fun partyViewModelFactory(): PartyViewModelFactory {

        val repository= Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return PartyViewModelFactory(repository)
    }

    fun partyMembersViewModelFactory(partyName: String): PartyMembersViewModelFactory {

        val repository= Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return PartyMembersViewModelFactory(repository, partyName)
    }
    fun memberViewModelFactory(memberID: Int): MemberViewModelFactory {

        val repository= Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return MemberViewModelFactory(repository, memberID)
    }
}

