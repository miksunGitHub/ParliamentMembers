package com.example.parliamentmembers.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup

import androidx.core.net.toUri

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.parliamentmembers.Functions

import com.example.parliamentmembers.R

import com.example.parliamentmembers.databinding.FragmentMemberBinding
import com.example.parliamentmembers.utilities.InjectorUtils

class MemberFragment : Fragment(), Functions {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding:FragmentMemberBinding=DataBindingUtil.inflate(
            inflater, R.layout.fragment_member, container,false)

        binding.setLifecycleOwner(this)

        val memberFragmentArgs= MemberFragmentArgs.fromBundle(requireArguments())

        var memberID=memberFragmentArgs.memberID

        val factory=InjectorUtils.memberViewModelFactory(memberID)

        val viewModel= ViewModelProviders.of(this, factory)
            .get(MemberViewModel::class.java)

        binding.memberViewModel= viewModel

        viewModel.getMemberById.observe(viewLifecycleOwner, Observer{
            binding.partyMemberFirstName.setText(it.first_name)
            binding.partyMemberLastName.setText(it.last_name)

            var ageText="Age: "+it.age

            binding.ageView.setText(ageText)

            var title=it.minister

            if(title){
                binding.titleView.setText(R.string.minister)
            }else binding.titleView.setText(R.string.pm)

            var party=it.party

            var partyRes: Int=partyStringToStringRes(party)

            binding.partyNameView.setText(partyRes)

            binding.constituencyView.setText(it.constituency)

            var rating=it.rating

            if(rating>0.0){
                var ratingBar=binding.ratingBar

                ratingBar.visibility=VISIBLE

                ratingBar.setRating(rating)}

            var review=it.review

            binding.ratingTextView.setText(review)

            var imageView=binding.memberFaceView

            var imgUrl="https://avoindata.eduskunta.fi/"+it.picture

            val imageUri=imgUrl.toUri().buildUpon().scheme("https").build()

            //https://proandroiddev.com/glideing-your-way-into-recyclerview-state-invalidation-5632fdb9be85
            Glide.with(imageView.context)
                .load(imageUri)
                .override(SIZE_ORIGINAL, 200)
                .apply(RequestOptions()
                    .placeholder(R.drawable.member_pic))
                .into(imageView)



            binding.rateButton.setOnClickListener(){
                this.findNavController().navigate(MemberFragmentDirections.actionMemberFragmentToRatingFragment(memberID))
            }
        })



        return binding.root
    }
}