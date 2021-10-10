package com.example.parliamentmembers.party

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

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Recycler view adapter for the party fragment

//References to outside sources used:
//https://www.andreasjakl.com/recyclerview-kotlin-style-click-listener-android/
//https://guides.codepath.com/android/using-the-recyclerview
//https://guides.codepath.com/android/endless-scrolling-with-adapterviews-and-recyclerview

class PartyViewRecyclerViewAdapter(
        private val partyList: List<PartyImageViewData>, private val clickListener: (PartyImageViewData)->Unit
) :
    RecyclerView.Adapter<PartyViewRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(partyImageViewData: PartyImageViewData, clickListener: (PartyImageViewData) -> Unit) {

            val partyNameView = itemView.findViewById<TextView>(R.id.partyText)
            partyNameView.setText(partyImageViewData.partyName)

            val partyLogoView = itemView.findViewById<ImageView>(R.id.partyLogoView)

            //Png-images of party logos are stored in the metropolia server
            var imgUrl="https://users.metropolia.fi/~a16002/pngs/"+partyImageViewData.partyLogo
            val imageUri=imgUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(partyLogoView.context)
                    .load(imageUri)
                    .override(Target.SIZE_ORIGINAL, 200)
                    .apply(
                            RequestOptions()
                                    .placeholder(R.drawable.member_pic))
                    .into(partyLogoView)

            itemView.setOnClickListener{clickListener(partyImageViewData)}
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.party_view, viewGroup,
                false
            )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return partyList.size
    }

    override fun onBindViewHolder(viewHolder: PartyViewRecyclerViewAdapter.ViewHolder, position: Int) {
        viewHolder.bind(partyList[position], clickListener)
    }

}