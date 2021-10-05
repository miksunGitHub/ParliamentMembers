package com.example.parliamentmembers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.FragmentRatingBinding
import com.example.parliamentmembers.utilities.InjectorUtils
import kotlin.math.roundToInt

class RatingFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentRatingBinding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_rating, container, false)

        binding.setLifecycleOwner(this)

        val memberFragmentArgs=MemberFragmentArgs.fromBundle(requireArguments())

        var memberID=memberFragmentArgs.memberID.toLong()

        val factory=InjectorUtils.ratingViewModelFactory(memberID)

        val viewModel= ViewModelProviders.of(this, factory)
            .get(RatingViewModel::class.java)


        binding.saveButton.setOnClickListener {
            viewModel.getMemberByID(memberID).observe(viewLifecycleOwner, Observer {

                var member = it.first()

                var review = binding.rateMemberEditText.text.toString()



                var rating=binding.ratingBar.rating.toFloat()

                var updatedParliamentMember: ParliamentMember = ParliamentMember(memberID.toInt(), member.first_name, member.last_name, member.age, member.party, member.constituency, member.minister, rating, review, member.picture)

                viewModel.updateMember(updatedParliamentMember)

            })
        }



        return binding.root
    }
}