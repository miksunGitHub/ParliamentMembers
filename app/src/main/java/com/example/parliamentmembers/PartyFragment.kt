package com.example.parliamentmembers

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.databinding.FragmentPartyBinding
import com.example.parliamentmembers.utilities.InjectorUtils



class PartyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPartyBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party, container, false)

        binding.setLifecycleOwner (this)

        val factory= InjectorUtils.partyViewModelFactory()

        val viewModel= ViewModelProviders.of(this, factory)
                .get(PartyViewModel::class.java)

        binding.partyViewModel= viewModel

        var parties= mutableSetOf<String>()

        var partyImageList= mutableListOf<PartyImageViewData>()

        viewModel.partyList.observe(viewLifecycleOwner, Observer {

            for (party in it) {
                var partyImg = when (party) {
                    "kok" -> R.drawable.kok
                    "sd" -> R.drawable.sdp
                    "kesk" -> R.drawable.kesk
                    "ps" -> R.drawable.perussuomalaiset
                    "vihr" -> R.drawable.vihrea
                    "vas" -> R.drawable.vas
                    "r" -> R.drawable.rkp
                    "kd" -> R.drawable.kristill
                    "liik" -> R.drawable.liikenyt
                    "vkk" -> R.drawable.vkk
                    else -> R.drawable.eduskunta
                }
                var partyName = when (party) {
                    "kok" -> R.string.kok_name
                    "sd" -> R.string.sd_name
                    "kesk" -> R.string.kesk_name
                    "ps" -> R.string.ps_name
                    "vihr" -> R.string.vihr_name
                    "vas" -> R.string.vas_name
                    "r" -> R.string.r_name
                    "kd" -> R.string.kd_name
                    "liik" -> R.string.liik_name
                    "vkk" -> R.string.vkk_name
                    else -> R.string.vkk_name
                }
                var partyImageViewData = PartyImageViewData(party, partyImg, partyName)

                partyImageList.add(partyImageViewData)

            }
            fun recyclerViewItemClicked(item: PartyImageViewData, ) {

                this.findNavController().navigate(PartyFragmentDirections.actionPartyFragmentToPartyMembersFragment(item.party))
            }

            binding.partyRecyclerView.layoutManager = LinearLayoutManager(view?.context)

            binding.partyRecyclerView.adapter = MyRecyclerViewAdapter(partyImageList,
                    {item: PartyImageViewData-> recyclerViewItemClicked(item)})


        })
        return binding.root
    }


    //https://www.andreasjakl.com/recyclerview-kotlin-style-click-listener-android/
    //https://guides.codepath.com/android/using-the-recyclerview

    class MyRecyclerViewAdapter(
            private val partyList: List<PartyImageViewData>, private val clickListener: (PartyImageViewData)->Unit
    ) :
            RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                fun bind(partyImageViewData: PartyImageViewData, clickListener: (PartyImageViewData) -> Unit) {

                    val partyNameView = itemView.findViewById<TextView>(R.id.partyText)
                    partyNameView.setText(partyImageViewData.partyName)

                    val partyLogoView = itemView.findViewById<ImageView>(R.id.partyLogoView)
                    partyLogoView.setImageResource(partyImageViewData.partyLogo)

                    itemView.setOnClickListener{clickListener(partyImageViewData)}
                }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyRecyclerViewAdapter.ViewHolder {
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

        override fun onBindViewHolder(viewHolder: MyRecyclerViewAdapter.ViewHolder, position: Int) {
            viewHolder.bind(partyList[position], clickListener)

            //val partyData=partyList.get(position)
    /*
            val partyNameView= viewHolder.partyNameView
            partyNameView.setText(partyData.partyName)

            val partyLogoView=viewHolder.partyPic
            partyLogoView.setImageResource(partyData.partyLogo)



            vh.itemView.findViewById<TextView>(R.id.partyText).text =
                    myList[pos]*/
        }

    }


}


