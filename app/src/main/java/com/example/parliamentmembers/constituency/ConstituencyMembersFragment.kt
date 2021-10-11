package com.example.parliamentmembers.constituency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.FragmentConstituencyMembersBinding
import com.example.parliamentmembers.utilities.InjectorUtils

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Class passes a list of parliament member objects to recycler view from a constituency, that was
//chosen in the constituency fragment. It observers a list of parliament members in the view model.

class ConstituencyMembersFragment : Fragment() {

    private lateinit var binding: FragmentConstituencyMembersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_constituency_members, container, false)

        binding.lifecycleOwner = this

        //The name of the constituency is fetched from safeArgs.
        val membersFragmentArgs= ConstituencyMembersFragmentArgs.fromBundle(requireArguments())
        val constituency=membersFragmentArgs.constituency

        //The name of the constituency is added to the up bar
        (activity as AppCompatActivity).supportActionBar?.title = constituency

        //Creates view model factory. The name of the constituency is passed to the view model
        //as a constructor parameter.
        val factory= InjectorUtils.constituencyMembersViewModelFactory(constituency)

        val viewModel= ViewModelProviders.of(this, factory)
            .get(ConstituencyMembersViewModel::class.java)

        binding.constituencyMembersViewModel=viewModel

        //The constituency live data in the view model is observed. The list of objects is passed to
        //recycler view adapter.
        viewModel.membersByConstituency.observe(viewLifecycleOwner, Observer {

            //On click listener for the recycler view object.
            fun recyclerViewItemClicked(item: ParliamentMember) {

                this.findNavController().navigate(ConstituencyMembersFragmentDirections.actionConstituencyMembersFragmentToMemberFragment(item.id))
            }

            //Live data is passed to recycler view usin the adapter.
            binding.constituencyMembersRecycleView.layoutManager = LinearLayoutManager(view?.context)
            binding.constituencyMembersRecycleView.adapter = ConstituencyMembersRecyclerViewAdapter(it
            ) { item: ParliamentMember -> recyclerViewItemClicked(item) }
        })

        return binding.root
   }
}