package com.example.parliamentmembers.partymembers

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentmembers.Functions
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databaseAndNetwork.PartyMembersRecyclerViewAdapter
import com.example.parliamentmembers.databinding.FragmentPartyMembersBinding
import com.example.parliamentmembers.utilities.InjectorUtils

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Fragment gets the party name from previous fragment, passes the name to view model and
//observers the live data created by a view model matching the party name. Passes the data
//to recycler view.

class PartyMembersFragment : Fragment(), Functions {

    private lateinit var binding: FragmentPartyMembersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_party_members, container, false)

        binding.lifecycleOwner = this

        //Gets the party chosen in the previous fragment from safeArgs
        val membersFragmentArgs= PartyMembersFragmentArgs.fromBundle(requireArguments())
        val partyChosen=membersFragmentArgs.partyID

        //The name of the party is added to the up bar
        val partyName=partyStringToPartyName(partyChosen)
        (activity as AppCompatActivity).supportActionBar?.title=partyName

        //Creates the view model factory with the party name as parameter
        val factory= InjectorUtils.partyMembersViewModelFactory(partyChosen)

        val viewModel= ViewModelProviders.of(this, factory)
                .get(PartyMembersViewModel::class.java)

        binding.partyMembersViewModel= viewModel

        //Observes the list of parliament member objects
        viewModel.parliamentMemberDataByParty.observe(viewLifecycleOwner, Observer{

            fun recyclerViewItemClicked(item: ParliamentMember) {

                //Navigates to the next fragment. Member id passed to safeArgs.
                this.findNavController().navigate(PartyMembersFragmentDirections.actionPartyMembersFragmentToMemberFragment(item.id))
            }

            //Data to recycler view adapter
            binding.partyMembersRecycleView.layoutManager = LinearLayoutManager(view?.context)
            binding.partyMembersRecycleView.adapter = PartyMembersRecyclerViewAdapter(it
            ) { item: ParliamentMember -> recyclerViewItemClicked(item) }

        })

        return binding.root
    }
}




