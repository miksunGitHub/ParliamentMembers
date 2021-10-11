package com.example.parliamentmembers.memberlist

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
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.FragmentMemberListBinding
import com.example.parliamentmembers.utilities.InjectorUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Class defines the contents of the member list fragment, that shows all the members of the
//finnish parliament.

class MemberListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentMemberListBinding =DataBindingUtil.inflate(
            inflater, R.layout.fragment_member_list, container, false)

        binding.lifecycleOwner = this

        //Sets the bottom navigation bar
        val navController=this.findNavController()
        var bottomNavigationView: BottomNavigationView =binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

        val factory=InjectorUtils.memberListViewModelFactory()

        val viewModel=ViewModelProviders.of(this, factory)
            .get(MemberListViewModel::class.java)

        binding.memberListViewModel=viewModel

        //Observers the list all parliment members objects in the database in the view model.
        viewModel.parliamentMemberData.observe(viewLifecycleOwner, Observer{

            //On click listener for the recycler view member
            fun recyclerViewItemClicked(item: ParliamentMember) {

                //Navigates to member fragment, passes the id of the member chosen to safeArgs.
                this.findNavController().navigate(MemberListFragmentDirections.actionMemberListFragmentToMemberFragment(item.id))
            }


            //Defines the grid layout
            val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

            //Passes data to recycler view adapter, binds the view with the adapter
            binding.memberListRecycleView.layoutManager = manager
            binding.memberListRecycleView.adapter = MemberListRecyclerViewAdapter(it
            ) { item: ParliamentMember -> recyclerViewItemClicked(item) }
        })

        return binding.root
    }

}

