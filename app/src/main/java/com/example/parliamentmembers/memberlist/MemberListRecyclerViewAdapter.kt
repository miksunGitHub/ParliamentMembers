package com.example.parliamentmembers.memberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.parliamentmembers.R
import com.example.parliamentmembers.constituency.ConstituencyMembersRecyclerViewAdapter
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.ConstituencyViewBinding
import com.example.parliamentmembers.databinding.MemberListViewBinding
import kotlinx.android.synthetic.main.member_list_view.view.*

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Recycler view adapter for the member list fragment

class MemberListRecyclerViewAdapter(
        private val partyMemberList: List<ParliamentMember>, private val clickListener: (ParliamentMember)->Unit
):
        RecyclerView.Adapter<MemberListRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = partyMemberList[position]

        viewHolder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)

    }

    class ViewHolder private constructor(val binding: MemberListViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private val firstNameView = binding.memberNameFirst

        private val lastNameView = binding.memberNameLast

        private val imageView = binding.memberImageView

        fun bind(item: ParliamentMember, clickListener: (ParliamentMember) -> Unit) {

            val firstName=item.first_name
            firstNameView.text = firstName

            val lastName=item.last_name
            lastNameView.text=lastName

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
                val binding = MemberListViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = partyMemberList.size
}
