package com.example.parliamentmembers.utilities

import com.example.parliamentmembers.*
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
    fun memberListViewModelFactory(): MemberListViewModelFactory {

        val repository= Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return MemberListViewModelFactory(repository)
    }
    fun ratingViewModelFactory(memberID: Long): RatingViewModelFactory {

        val repository= Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return RatingViewModelFactory(repository, memberID)
    }

}

