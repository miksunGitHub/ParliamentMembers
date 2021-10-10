package com.example.parliamentmembers.databaseAndNetwork

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentmembers.MyApp

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Class mostly copied from materials provided by Metropolia University of Applied Sciencies

@Database(entities=[ParliamentMember::class], version=3, exportSchema=false)
abstract class ParliamentMemberDataBase: RoomDatabase() {
    abstract val parliamentMemberDao: ParliamentMemberDao
    companion object{
        @Volatile
        private var INSTANCE: ParliamentMemberDataBase? =null
        fun getInstance(): ParliamentMemberDataBase{
            synchronized(this){
                var instance= INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        MyApp.appContext,
                        ParliamentMemberDataBase::class.java, "member_data")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}