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

    fun fetchData(viewModelScope: CoroutineScope): MutableLiveData<List<ParliamentMemberJsonData>>{

        var parliamentMemberData=MutableLiveData<List<ParliamentMemberJsonData>>()

        viewModelScope.launch{
            try{
                parliamentMemberData.value= ParliamentApi.retrofitService.getProperties()
                Log.d("#####","ok")

            } catch(e: Exception){

                parliamentMemberData.value=ArrayList()
                Log.d("#####", e.toString())
            }

            parliamentMemberData.value?.forEach{
                var firstName=it.first
                var lastName=it.last
                var age=2020-it.bornYear
                var constituency=it.constituency
                var party=it.party
                var minister=it.minister
                var picture=it.picture

                var parliamentMember= ParliamentMember(first_name = firstName, last_name = lastName, age = age, party = party, constituency = constituency, minister = minister, rating = 0f, review = "", picture = picture)

                viewModelScope.launch {
                    ParliamentMemberDataBase.getInstance().parliamentMemberDao.insert(parliamentMember)
                }
            }
    }
        return parliamentMemberData
    }
/*
    fun storeImgCaches(imageView: ImageView){
        var data=parliamentMemberDao.getAll()

        data.value?.forEach {
            var imageUrl="https://avoindata.eduskunta.fi/"+it.picture

            val imageUri=imageUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(imageView.context)
                .load(imageUri)
                .apply(RequestOptions()
                    .override(SIZE_ORIGINAL, 200)
                    .placeholder(R.drawable.member_pic))
                    .into(imageView)
        }

    }*/

    fun getMemberByID(id: Long): LiveData<List<ParliamentMember>>{

        return parliamentMemberDao.getMemberByID(id)}



    fun updateMember(parliamentMember: ParliamentMember, viewModelScope: CoroutineScope) {

        viewModelScope.launch {
            try {
                parliamentMemberDao.updateMember(parliamentMember)
            }catch(e: Exception){
                Log.d("?????????", e.toString())
            }

        }
    }
    }





/*
    //https://developer.android.com/topic/performance/graphics/cache-bitmap
    fun storePictures(){
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        // Use 1/8th of the available memory for this memory cache.
        val cacheSize = maxMemory / 8

        val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.byteCount / 1024
            }
        }




    }
    fun loadBitmap(resId: Int, imageView: ImageView) {
        val imageKey: String = resId.toString()

        val bitmap: Bitmap? = getBitmapFromMemCache(imageKey)?.also {
            imageView.setImageBitmap(it)
        } ?: run {
            imageView.setImageResource(R.drawable.member_pic)
            val task = BitmapWorkerTask()
            task.execute(resId)
            null
        }
    }


    private inner class BitmapWorkerTask : AsyncTask<Int, Unit, Bitmap>() {
        ...
        // Decode image in background.
        override fun doInBackground(vararg params: Int?): Bitmap? {
            return params[0]?.let { imageId ->
                decodeSampledBitmapFromResource(resources, imageId, 100, 100)?.also { bitmap ->
                    addBitmapToMemoryCache(imageId.toString(), bitmap)
                }
            }
        }

    }

}*/



