package com.example.parliamentmembers.party

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentmembers.*
import com.example.parliamentmembers.databinding.FragmentPartyBinding
import com.example.parliamentmembers.utilities.InjectorUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Class for the party image fragment, which is the first fragment shown, when the app starts.
//List of objects observed from view model and passed to recycler view adapter. Menus created.

class PartyFragment : Fragment() {

    private lateinit var binding: FragmentPartyBinding

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_party, container, false)

        binding.lifecycleOwner = this

        val factory= InjectorUtils.partyViewModelFactory()

        val viewModel= ViewModelProviders.of(this, factory)
                .get(PartyViewModel::class.java)

        binding.partyViewModel= viewModel

        //Sets the bottom navigation bar
        bottomNavigationView =binding.bottomNavigationView
        val navController=this.findNavController()
        bottomNavigationView.setupWithNavController(navController)

        //List of objects passed to recycler view adapter
        val partyListData=mutableSetOf<PartyImageViewData>()

        viewModel.partyList.observe(viewLifecycleOwner, Observer{ list ->

            list.forEach(){

                partyListData.add(it)
            }

            //On click listener for the recycler view member
            fun recyclerViewItemClicked(item: PartyImageViewData) {

                //Navigates to party members fragment, party name is added to safeArgs.
                this.findNavController().navigate(PartyFragmentDirections.actionPartyFragmentToPartyMembersFragment(item.party))
            }

            //Set is transformed to a list
            var partyListDataToRecyclerView=partyListData.toList()

            //The list is sorted by the count integers
            partyListDataToRecyclerView=partyListDataToRecyclerView.sortedBy {
                it.count  }

            //The list and the on click listener are passed to recycler view adapter
            binding.partyRecyclerView.layoutManager = LinearLayoutManager(view?.context)
            binding.partyRecyclerView.adapter = PartyViewRecyclerViewAdapter(partyListDataToRecyclerView
            ) { item: PartyImageViewData -> recyclerViewItemClicked(item) }

        })

        setHasOptionsMenu(true)

        return binding.root
    }

    //Sets the options menu to the fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu2, menu)
    }
}


