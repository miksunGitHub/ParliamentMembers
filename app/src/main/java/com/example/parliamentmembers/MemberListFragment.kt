package com.example.parliamentmembers

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
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.FragmentMemberListBinding
import com.example.parliamentmembers.utilities.InjectorUtils

class MemberListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentMemberListBinding =DataBindingUtil.inflate(
                inflater, R.layout.fragment_member_list, container, false)

        binding.setLifecycleOwner(this)

        val factory=InjectorUtils.memberListViewModelFactory()

        val viewModel=ViewModelProviders.of(this, factory)
                .get(MemberListViewModel::class.java)

        binding.memberListViewModel=viewModel

        viewModel.parliamentMemberData.observe(viewLifecycleOwner, Observer{
            fun recyclerViewItemClicked(item: ParliamentMember) {

                Log.d("#####", "Clicked")

                //this.findNavController().navigate(PartyMembersFragmentDirections.actionPartyMembersFragmentToMemberFragment(item.id))
            }

            binding.partyMembersRecycleView.adapter = MemberListRecyclerViewAdapter(it,
                    { item: ParliamentMember -> recyclerViewItemClicked(item) })


        })

        return binding.root
    }

}

class MemberListRecyclerViewAdapter(
        private val partyMemberList: List<ParliamentMember>, private val clickListener: (ParliamentMember)->Unit
) :
        RecyclerView.Adapter<MemberListRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(parliamentMember: ParliamentMember, clickListener: (ParliamentMember) -> Unit) {

            val firstNameView = itemView.findViewById<TextView>(R.id.memberFirstName)
            firstNameView.setText(parliamentMember.first_name)

            val lastNameView = itemView.findViewById<TextView>(R.id.memberLastName)
            lastNameView.setText(parliamentMember.last_name)

            itemView.setOnClickListener{clickListener(parliamentMember)}
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MemberListRecyclerViewAdapter.ViewHolder {
        val itemView =
                LayoutInflater.from(viewGroup.context).inflate(
                        R.layout.member_list_view, viewGroup,
                        false
                )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return partyMemberList.size
    }

    override fun onBindViewHolder(viewHolder: MemberListRecyclerViewAdapter.ViewHolder, position: Int) {
        viewHolder.bind(partyMemberList[position], clickListener)
    }

}