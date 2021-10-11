package com.example.parliamentmembers.constituency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.ConstituencyViewBinding

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Recycler view adapter for the constituency members fragment

class ConstituencyMembersRecyclerViewAdapter(
        private val partyMemberList: List<ParliamentMember>, private val clickListener: (ParliamentMember)->Unit
):
        RecyclerView.Adapter<ConstituencyMembersRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = partyMemberList[position]

        viewHolder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ConstituencyViewBinding) : RecyclerView.ViewHolder(binding.root) {

        val nameView = binding.constMemberName

        val imageView = binding.constImageView

        fun bind(item: ParliamentMember, clickListener: (ParliamentMember) -> Unit) {

            val firstName = item.first_name + " " + item.last_name
            nameView.text = firstName

            var imgUrl = "https://avoindata.eduskunta.fi/" + item.picture
            val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(imageView.context)
                    .load(imageUri)
                    .override(Target.SIZE_ORIGINAL, 200)
                    .apply(
                            RequestOptions()
                                    .placeholder(R.drawable.member_pic))
                    .into(imageView)

            itemView.setOnClickListener { clickListener(item) }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ConstituencyViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = partyMemberList.size
}