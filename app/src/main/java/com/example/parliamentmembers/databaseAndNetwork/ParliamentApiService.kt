package com.example.parliamentmembers.databaseAndNetwork

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://users.metropolia.fi/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ParliamentApiService{
    @GET("~a16002/mps/mps.json")
    suspend fun getProperties():
            List<ParliamentMemberJsonData>
}

object ParliamentApi{
    val retrofitService: ParliamentApiService by lazy{
        retrofit.create(ParliamentApiService::class.java)
    }
}