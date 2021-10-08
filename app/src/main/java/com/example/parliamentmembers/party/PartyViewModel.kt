package com.example.parliamentmembers.party

import androidx.lifecycle.*
import com.example.parliamentmembers.Functions
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.*


class PartyViewModel(private val repository: Repository): ViewModel(), Functions {



        init {
                repository.fetchData(viewModelScope)
        }

        val partyList=Transformations.map(repository.getParties()) {
                it.distinct().map {
                        redefinePartyData(it)
                }
        }

        fun redefinePartyData(party: String): PartyImageViewData {

                val partyImageViewData: PartyImageViewData

                var partyImg=partyStringToDrawable(party)

                var partyName=partyStringToStringRes(party)

                var orderCount=sortParties(party)

                partyImageViewData= PartyImageViewData(orderCount, party, partyImg, partyName)

                return partyImageViewData
        }

}

