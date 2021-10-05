package com.example.parliamentmembers


import android.widget.ImageView
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.parliamentmembers.databaseAndNetwork.Repository

class MemberViewModel(private val repository: Repository, private val memberID: Int): ViewModel() {


    //fun storeImgCaches(imageView: ImageView)=repository.storeImgCaches(imageView)

    val parliamentMemberDataByID=Transformations.map(repository.getAll()){ it.filter{it.id==memberID} }



}