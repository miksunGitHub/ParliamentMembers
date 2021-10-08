package com.example.parliamentmembers.party


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentmembers.*

import com.example.parliamentmembers.databinding.FragmentPartyBinding
import com.example.parliamentmembers.utilities.InjectorUtils
import com.google.android.material.bottomnavigation.BottomNavigationView


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

        var bottomNavigationView: BottomNavigationView =binding.bottomNavigationView

        val navCotroller=this.findNavController()

        bottomNavigationView.setupWithNavController(navCotroller)


        var partyListData=mutableSetOf<PartyImageViewData>()

        viewModel.partyList.observe(viewLifecycleOwner, Observer{

            it.forEach(){

                partyListData.add(it)
            }

            fun recyclerViewItemClicked(item: PartyImageViewData) {

                this.findNavController().navigate(PartyFragmentDirections.actionPartyFragmentToPartyMembersFragment(item.party))
            }

            var partyListDataToRV=partyListData.toList()

            partyListDataToRV=partyListDataToRV.sortedBy {
                it.count  }

            binding.partyRecyclerView.layoutManager = LinearLayoutManager(view?.context)

            binding.partyRecyclerView.adapter = PartyViewRecyclerViewAdapter(partyListDataToRV,
                {item: PartyImageViewData -> recyclerViewItemClicked(item)})

        })

        return binding.root
    }
}


