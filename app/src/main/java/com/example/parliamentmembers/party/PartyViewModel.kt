package com.example.parliamentmembers.party

import androidx.lifecycle.*
import com.example.parliamentmembers.Functions
import com.example.parliamentmembers.databaseAndNetwork.*

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//View model for the party fragment. Live data list of party names is fetched from the database
//using the repository and transformed into an object, that has parameters for the views
//shown in the recycler view.

class PartyViewModel(repository: Repository): ViewModel(), Functions {

        //Data from the web server is fetched from the web server, when the view model is initialized
        init {
                repository.fetchData(viewModelScope)
        }

        //Transforms a list of live data strings into list of live data objects
        val partyList=Transformations.map(repository.getParties()) { list ->
                list.distinct().map {
                        redefinePartyData(it)
                }
        }

        //Party name string is transformed into an object, that has the properties added to the view
        //shown in the recycler view.
        private fun redefinePartyData(party: String): PartyImageViewData {

                val partyImageViewData: PartyImageViewData

                //matches the party name with the end of the url of the image source
                var partyImg=partyStringToUrl(party)

                //matches the party name with the party string in the resources
                var partyName=partyStringToStringRes(party)

                //the list members are given an integer to define the order in the recycler view
                var orderCount=sortParties(party)

                //Creates the object
                partyImageViewData= PartyImageViewData(orderCount, party, partyImg, partyName)

                return partyImageViewData
        }
}

