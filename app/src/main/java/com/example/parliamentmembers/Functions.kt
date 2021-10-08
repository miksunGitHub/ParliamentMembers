package com.example.parliamentmembers

import android.view.View

interface Functions {

    fun partyStringToDrawable(partyName: String): Int{
        var partyImg = when (partyName) {
            "kok" -> return R.drawable.kok
            "sd" -> return  R.drawable.sdp
            "kesk" -> return  R.drawable.kesk
            "ps" -> return  R.drawable.perussuomalaiset
            "vihr" -> return  R.drawable.vihrea
            "vas" -> return  R.drawable.vas
            "r" -> return  R.drawable.rkp
            "kd" -> return  R.drawable.kristill
            "liik" -> return  R.drawable.liikenyt

            else -> return  R.drawable.eduskunta
        }
    }

    fun partyStringToStringRes(partyName: String): Int {
        var partyName = when (partyName) {

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

    fun sortParties(partyName: String): Int{
        var partyName = when (partyName) {
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
}