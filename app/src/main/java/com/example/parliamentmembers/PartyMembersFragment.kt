package com.example.parliamentmembers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember

import com.example.parliamentmembers.databinding.FragmentPartyMembersBinding
import com.example.parliamentmembers.utilities.InjectorUtils

class PartyMembersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentPartyMembersBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party_members, container, false)

        binding.setLifecycleOwner (this)

        val membersFragmentArgs=PartyMembersFragmentArgs.fromBundle(requireArguments())

        var partyChosen=membersFragmentArgs.partyID

        val factory= InjectorUtils.partyMembersViewModelFactory(partyChosen)

        val viewModel= ViewModelProviders.of(this, factory)
                .get(PartyMembersViewModel::class.java)

        binding.partyMembersViewModel= viewModel

        viewModel.parliamentMemberDataByParty.observe(viewLifecycleOwner, Observer{
            fun recyclerViewItemClicked(item: ParliamentMember) {

                Log.d("#####", "Clicked")

                this.findNavController().navigate(PartyMembersFragmentDirections.actionPartyMembersFragmentToMemberFragment(item.id))
            }

            binding.partyMembersRecycleView.layoutManager = LinearLayoutManager(view?.context)

            binding.partyMembersRecycleView.adapter = PartyMemberRecyclerViewAdapter(it,
                { item: ParliamentMember -> recyclerViewItemClicked(item) })



        })

        return binding.root
    }



}

class PartyMemberRecyclerViewAdapter(
    private val partyMemberList: List<ParliamentMember>, private val clickListener: (ParliamentMember)->Unit
) :
    RecyclerView.Adapter<PartyMemberRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(parliamentMember: ParliamentMember, clickListener: (ParliamentMember) -> Unit) {

            val firstNameView = itemView.findViewById<TextView>(R.id.partyMemberFirstName)
            firstNameView.setText(parliamentMember.first_name)

            val lastNameView = itemView.findViewById<TextView>(R.id.partyMemberLastName)
            lastNameView.setText(parliamentMember.last_name)

            itemView.setOnClickListener{clickListener(parliamentMember)}
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PartyMemberRecyclerViewAdapter.ViewHolder {
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

    override fun onBindViewHolder(viewHolder: PartyMemberRecyclerViewAdapter.ViewHolder, position: Int) {
        viewHolder.bind(partyMemberList[position], clickListener)
    }

}


