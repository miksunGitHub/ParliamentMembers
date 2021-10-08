package com.example.parliamentmembers.databaseAndNetwork

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

class PartyMembersRecyclerViewAdapter(
    private val partyMemberList: List<ParliamentMember>, private val clickListener: (ParliamentMember)->Unit
) :
    RecyclerView.Adapter<PartyMembersRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(parliamentMember: ParliamentMember, clickListener: (ParliamentMember) -> Unit) {

            val firstNameView = itemView.findViewById<TextView>(R.id.partyMemberFirstName)
            firstNameView.setText(parliamentMember.first_name)

            val lastNameView = itemView.findViewById<TextView>(R.id.partyMemberLastName)
            lastNameView.setText(parliamentMember.last_name)

            val imageView=itemView.findViewById<ImageView>(R.id.memberImageView)
            var imgUrl="https://avoindata.eduskunta.fi/"+parliamentMember.picture
            val imageUri=imgUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(imageView.context)
                .load(imageUri)
                .override(Target.SIZE_ORIGINAL, 200)
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.member_pic))
                .into(imageView)


            itemView.setOnClickListener{clickListener(parliamentMember)}
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PartyMembersRecyclerViewAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.party_members_view, viewGroup,
                false
            )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return partyMemberList.size
    }

    override fun onBindViewHolder(viewHolder: PartyMembersRecyclerViewAdapter.ViewHolder, position: Int) {
        viewHolder.bind(partyMemberList[position], clickListener)
    }
}