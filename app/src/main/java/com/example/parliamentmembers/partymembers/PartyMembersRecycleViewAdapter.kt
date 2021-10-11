package com.example.parliamentmembers.databaseAndNetwork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databinding.PartyMembersViewBinding

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Recycler view adapter for the party members fragment

class PartyMembersRecyclerViewAdapter(
        private val partyMemberList: List<ParliamentMember>, private val clickListener: (ParliamentMember)->Unit
):
        RecyclerView.Adapter<PartyMembersRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = partyMemberList[position]

        viewHolder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)

    }

    class ViewHolder private constructor(val binding: PartyMembersViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private val firstNameView = binding.partyMemberName

        private val imageView=binding.memberImageView

        fun bind(item: ParliamentMember, clickListener: (ParliamentMember) -> Unit) {

            val name=item.first_name+" "+item.last_name
            firstNameView.text = name

            val imgUrl="https://avoindata.eduskunta.fi/"+item.picture
            val imageUri=imgUrl.toUri().buildUpon().scheme("https").build()

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
                val binding = PartyMembersViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = partyMemberList.size
}
