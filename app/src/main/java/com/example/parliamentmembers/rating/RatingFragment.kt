package com.example.parliamentmembers.rating

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.FragmentRatingBinding
import com.example.parliamentmembers.member.MemberFragmentArgs
import com.example.parliamentmembers.utilities.InjectorUtils


class RatingFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentRatingBinding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_rating, container, false)

        binding.setLifecycleOwner(this)

        val memberFragmentArgs= MemberFragmentArgs.fromBundle(requireArguments())

        var memberID=memberFragmentArgs.memberID

        val factory=InjectorUtils.ratingViewModelFactory(memberID)

        val viewModel= ViewModelProviders.of(this, factory)
            .get(RatingViewModel::class.java)


        binding.saveButton.setOnClickListener {
            viewModel.getMemberByID(memberID.toLong()).observe(viewLifecycleOwner, Observer {

                var review = binding.rateMemberEditText.text.toString()

                var rating=binding.ratingBar.rating.toFloat()

                var updatedParliamentMember = ParliamentMember( memberID, it.member_num, it.first_name, it.last_name, it.age, it.party, it.constituency, it.minister, rating, review, it.picture)

                viewModel.updateMember(updatedParliamentMember)

                this.findNavController().navigate(RatingFragmentDirections.actionRatingFragmentToMemberFragment(memberID))
            })
        }

        return binding.root
    }
}