package com.example.parliamentmembers.memberlist


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.FragmentMemberListBinding
import com.example.parliamentmembers.memberlist.MemberListViewModel
import com.example.parliamentmembers.utilities.InjectorUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MemberListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentMemberListBinding =DataBindingUtil.inflate(
            inflater, R.layout.fragment_member_list, container, false)

        binding.setLifecycleOwner(this)






        var bottomNavigationView: BottomNavigationView =binding.bottomNavigationView

        val navCotroller=this.findNavController()

        bottomNavigationView.setupWithNavController(navCotroller)

        val factory=InjectorUtils.memberListViewModelFactory()

        val viewModel=ViewModelProviders.of(this, factory)
            .get(MemberListViewModel::class.java)

        binding.memberListViewModel=viewModel

        viewModel.parliamentMemberData.observe(viewLifecycleOwner, Observer{
            fun recyclerViewItemClicked(item: ParliamentMember) {

                Log.d("#####", "Clicked")



                this.findNavController().navigate(MemberListFragmentDirections.actionMemberListFragmentToMemberFragment(item.id))
            }
            val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

            binding.memberListRecycleView.layoutManager = manager

            binding.memberListRecycleView.adapter = MemberListRecyclerViewAdapter(it,
                { item: ParliamentMember -> recyclerViewItemClicked(item) })
        })

        return binding.root
    }

}

