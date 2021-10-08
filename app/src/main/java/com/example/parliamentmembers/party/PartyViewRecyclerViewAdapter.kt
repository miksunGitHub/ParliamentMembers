package com.example.parliamentmembers.party

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.R

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
            partyLogoView.setImageResource(partyImageViewData.partyLogo)

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