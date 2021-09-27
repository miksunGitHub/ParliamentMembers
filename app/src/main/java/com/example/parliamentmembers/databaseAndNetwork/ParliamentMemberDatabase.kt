package com.example.parliamentmembers.databaseAndNetwork

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentmembers.MyApp

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