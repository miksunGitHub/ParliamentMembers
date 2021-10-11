package com.example.parliamentmembers.party

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databinding.PartyViewBinding

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Recycler view adapter for the party fragment

//References to outside sources used:
//https://www.andreasjakl.com/recyclerview-kotlin-style-click-listener-android/
//https://guides.codepath.com/android/using-the-recyclerview
//https://guides.codepath.com/android/endless-scrolling-with-adapterviews-and-recyclerview
//https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals?index=..%2F..android-kotlin-fundamentals#0

class PartyViewRecyclerViewAdapter(
        private val partyList: List<PartyImageViewData>, private val clickListener: (PartyImageViewData)->Unit
):
        RecyclerView.Adapter<PartyViewRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = partyList[position]

        viewHolder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)

    }

    class ViewHolder private constructor(val binding: PartyViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private val partyNameView = binding.partyText

        private val partyLogoView = binding.partyLogoView

        fun bind(item: PartyImageViewData, clickListener: (PartyImageViewData) -> Unit) {

            partyNameView.setText(item.partyName)

            var imgUrl = "https://users.metropolia.fi/~a16002/pngs/" + item.partyLogo
            val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(partyLogoView.context)
                    .load(imageUri)
                    .override(Target.SIZE_ORIGINAL, 200)
                    .apply(
                            RequestOptions()
                                    .placeholder(R.drawable.member_pic))
                    .into(partyLogoView)

            itemView.setOnClickListener { clickListener(item) }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PartyViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = partyList.size

}