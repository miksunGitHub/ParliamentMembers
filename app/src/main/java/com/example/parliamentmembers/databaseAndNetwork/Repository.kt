package com.example.parliamentmembers.databaseAndNetwork

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.parliamentmembers.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class Repository private constructor(private val parliamentMemberDao: ParliamentMemberDao){

    fun getParties()=parliamentMemberDao.getParties()

    companion object{
        @Volatile private var instance: Repository?=null

        fun getInstance(parliamentMemberDao: ParliamentMemberDao)=
            instance?: synchronized(this){

                instance?: Repository(parliamentMemberDao).also{ instance=it}
            }
    }

    fun getAll()=parliamentMemberDao.getAll()

    fun fetchData(viewModelScope: CoroutineScope) {

        var parliamentMemberData = MutableLiveData<List<ParliamentMemberJsonData>>()

        viewModelScope.launch {
            try {
                parliamentMemberData.value = ParliamentApi.retrofitService.getProperties()
                Log.d("#####", "ok")

            } catch (e: Exception) {

                parliamentMemberData.value = ArrayList()
                Log.d("#####", e.toString())
            }
            saveData(parliamentMemberData, viewModelScope)
        }



    }
    fun saveData(data: LiveData<List<ParliamentMemberJsonData>>, viewModelScope: CoroutineScope){

        data.value?.forEach{
                var firstName=it.first
                var lastName=it.last
                var age=2020-it.bornYear
                var constituency=it.constituency
                var party=it.party
                var minister=it.minister
                var picture=it.picture

                var parliamentMember= ParliamentMember(first_name = firstName, last_name = lastName, age = age, party = party, constituency = constituency, minister = minister, rating = 0f, review = "", picture = picture)

                viewModelScope.launch {
                    try{
                    ParliamentMemberDataBase.getInstance().parliamentMemberDao.insert(parliamentMember)
                        Log.d("ok","ok")

                    }catch (e: Exception){
                        Log.d("error", e.toString())

                    }


                }
            }
    }

    fun getMemberByID(id: Long)=parliamentMemberDao.getMemberByID(id)

    fun updateMember(parliamentMember: ParliamentMember) {

        GlobalScope.launch {
            try {
                parliamentMemberDao.updateMember(parliamentMember)
            }catch(e: Exception){
                Log.d("?????????", e.toString())
            }

        }
    }
}




