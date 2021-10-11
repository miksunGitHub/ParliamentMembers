package com.example.parliamentmembers

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Functions storage

interface Functions {

    //Returns a string resource matching party name found in the database
    fun partyStringToStringRes(partyName: String): Int {
        return when (partyName) {

            "kok" -> R.string.kok_name
            "sd" -> R.string.sd_name
            "kesk" -> R.string.kesk_name
            "ps" -> R.string.ps_name
            "vihr" -> R.string.vihr_name
            "vas" -> R.string.vas_name
            "r" -> R.string.r_name
            "kd" -> R.string.kd_name
            "liik"-> R.string.liik_name

            else -> R.string.vkk_name
        }
    }

    //The order of parties in the party fragment. The order is defined by the results of
    //the latest election. From largest parliament group to largest.
    fun sortParties(partyName: String): Int{
        return when (partyName) {
            "sd" -> 1
            "ps" -> 2
            "kok" -> 3
            "kesk" -> 4
            "vihr" -> 5
            "vas" -> 6
            "r" -> 7
            "kd" -> 8
            "liik"-> 9

            else -> 10
        }
    }

    //If an review has been already stored in the database, adds the new review added with two
    //new line characters attached.
    fun concatenateReviews(previous: String, current: String): String{
        val previousReviews=previous

        val newReview: String

        newReview = if(previousReviews==""){
            current

        }else{
            previousReviews+"\n\n"+current
        }

        return newReview
    }

    fun partyStringToUrl(partyName: String): String{
        return when (partyName) {

            "kok" -> "kok.png"
            "sd" -> "sdp.png"
            "kesk" -> "kesk.png"
            "ps" -> "perussuomalaiset.png"
            "vihr" -> "vihrea.png"
            "vas" -> "vas.png"
            "r" -> "rkp.png"
            "kd" -> "kristill.png"
            "liik" -> "liikenyt.png"

            else -> "vkk.png"
        }
    }

    fun partyStringToPartyName(name: String): String{
        return when (name) {

            "kok" -> "National Coalition Party"
            "sd" -> "Social Democratic Party"
            "kesk" -> "Centre Party"
            "ps" -> "Finns Party"
            "vihr" -> "Green League"
            "vas" -> "Left Alliance"
            "r" -> "Swedish People´s Party"
            "kd" -> "Christian Democrats"
            "liik"-> "Movement Now"

            else -> "Power Belongs To The People"
        }



    }
}