package com.example.parliamentmembers.partymembers

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.parliamentmembers.R

import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.PartyMembersRecyclerViewAdapter

import com.example.parliamentmembers.databinding.FragmentPartyMembersBinding
import com.example.parliamentmembers.utilities.InjectorUtils

class PartyMembersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentPartyMembersBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party_members, container, false)

        binding.setLifecycleOwner (this)

        val membersFragmentArgs= PartyMembersFragmentArgs.fromBundle(requireArguments())

        var partyChosen=membersFragmentArgs.partyID

        val factory= InjectorUtils.partyMembersViewModelFactory(partyChosen)

        val viewModel= ViewModelProviders.of(this, factory)
                .get(PartyMembersViewModel::class.java)

        binding.partyMembersViewModel= viewModel

        viewModel.parliamentMemberDataByParty.observe(viewLifecycleOwner, Observer{
            fun recyclerViewItemClicked(item: ParliamentMember) {

                this.findNavController().navigate(PartyMembersFragmentDirections.actionPartyMembersFragmentToMemberFragment(item.id))
            }

            binding.partyMembersRecycleView.layoutManager = LinearLayoutManager(view?.context)

            binding.partyMembersRecycleView.adapter = PartyMembersRecyclerViewAdapter(it,
                { item: ParliamentMember -> recyclerViewItemClicked(item) })

        })

        return binding.root
    }
}




