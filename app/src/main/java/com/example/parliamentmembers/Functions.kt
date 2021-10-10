package com.example.parliamentmembers

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Functions storage

interface Functions {

    //Returns a string resource mathing party name found in the database
    fun partyStringToStringRes(partyName: String): Int {
        when (partyName) {

            "kok" -> return R.string.kok_name
            "sd" -> return R.string.sd_name
            "kesk" -> return R.string.kesk_name
            "ps" -> return R.string.ps_name
            "vihr" -> return  R.string.vihr_name
            "vas" -> return  R.string.vas_name
            "r" -> return  R.string.r_name
            "kd" -> return  R.string.kd_name
            "liik"-> return R.string.liik_name

            else -> return R.string.vkk_name
        }
    }

    //The order of parties in the party fragment. The order is defined by the results of
    //the latest election. From largest parliament group to largest.
    fun sortParties(partyName: String): Int{
        when (partyName) {
            "sd" -> return 1
            "ps" -> return 2
            "kok" -> return 3
            "kesk" -> return 4
            "vihr" -> return  5
            "vas" -> return  6
            "r" -> return  7
            "kd" -> return  8
            "liik"-> return 9

            else -> return 10
        }
    }

    //If an review has been already stored in the database, adds the new review added with two
    //new line characters attached.
    fun concatenateReviews(previous: String, current: String): String{
        var previousReviews=previous

        var newReview: String

        newReview = if(previousReviews==""){
            current

        }else{
            previousReviews+"\n\n"+current
        }

        return newReview
    }

    fun partyStringToUrl(partyName: String): String{
        when (partyName) {

            "kok" -> return "kok.png"
            "sd" -> return "sdp.png"
            "kesk" -> return "kesk.png"
            "ps" -> return "perussuomalaiset.png"
            "vihr" -> return "vihrea.png"
            "vas" -> return "vas.png"
            "r" -> return "rkp.png"
            "kd" -> return "kristill.png"
            "liik" -> return "liikenyt.png"

            else -> return "vkk.png"
        }
    }
}