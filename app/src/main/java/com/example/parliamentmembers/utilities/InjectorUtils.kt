package com.example.parliamentmembers.utilities

import com.example.parliamentmembers.name.NameViewModelFactory
import com.example.parliamentmembers.constituency.ConstituencyMembersViewModelFactory
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMemberDataBase
import com.example.parliamentmembers.databaseAndNetwork.Repository
import com.example.parliamentmembers.member.MemberViewModelFactory
import com.example.parliamentmembers.memberlist.MemberListViewModelFactory
import com.example.parliamentmembers.party.PartyViewModelFactory
import com.example.parliamentmembers.partymembers.PartyMembersViewModelFactory
import com.example.parliamentmembers.rating.RatingViewModelFactory

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Functions to pass the view models with a reference to the repository and parameters to
//needed to pass data from the repository.

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

    fun ratingViewModelFactory(memberID: Int): RatingViewModelFactory {

        val repository= Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return RatingViewModelFactory(repository, memberID)
    }


    fun memberListViewModelFactory(): MemberListViewModelFactory {

        val repository =
            Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return MemberListViewModelFactory(repository) }

    fun constituencyMembersViewModelFactory(constituency: String): ConstituencyMembersViewModelFactory {

        val repository =
            Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return ConstituencyMembersViewModelFactory(repository, constituency)}

    fun nameViewModelFactory(firstName: String, lastName: String): NameViewModelFactory {

        val repository =
            Repository.getInstance(ParliamentMemberDataBase.getInstance().parliamentMemberDao)

        return NameViewModelFactory(repository, firstName, lastName)
    }
}
