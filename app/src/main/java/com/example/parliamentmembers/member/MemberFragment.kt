package com.example.parliamentmembers.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Class defines the contents of the views in the member fragment.

class MemberFragment : Fragment(), Functions {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding:FragmentMemberBinding=DataBindingUtil.inflate(
            inflater, R.layout.fragment_member, container,false)

        binding.lifecycleOwner = this

        //The id of the member chosen in the previous fragment is gotten from the safeArgs.
        val memberFragmentArgs= MemberFragmentArgs.fromBundle(requireArguments())
        var memberID=memberFragmentArgs.memberID

        val factory=InjectorUtils.memberViewModelFactory(memberID)

        val viewModel= ViewModelProviders.of(this, factory)
            .get(MemberViewModel::class.java)

        binding.memberViewModel= viewModel

        //Observers the live data object in the view model, defines the contents of the view to
        //match the object
        viewModel.getMemberById.observe(viewLifecycleOwner, Observer{

            var name=it.first_name+" "+it.last_name

            binding.partyMembersName.text =name

            //The name of the member is added to the up bar
            (activity as AppCompatActivity).supportActionBar?.title=name

            var ageText="Age: "+it.age

            binding.ageView.text = ageText

            var title=it.minister

            //Defines which string resource is shown in the title view.
            if(title){
                binding.titleView.setText(R.string.minister)
            }else binding.titleView.setText(R.string.pm)

            var party=it.party

            var partyRes: Int=partyStringToStringRes(party)

            binding.partyNameView.setText(partyRes)

            binding.constituencyView.text = it.constituency

            var rating=it.rating

            //If the member has been rated, the rating bar is show with rating given.
            if(rating>0.0){
                var ratingBar=binding.ratingBar

                ratingBar.visibility=VISIBLE

                ratingBar.setRating(rating)}

            //
            var review=it.review
            binding.ratingTextView.text = review

            //Glide used to set a picture of the member from an Url to the image view
            var imageView=binding.memberFaceView

            var imgUrl="https://avoindata.eduskunta.fi/"+it.picture

            val imageUri=imgUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(imageView.context)
                .load(imageUri)
                .override(SIZE_ORIGINAL, 200)
                .apply(RequestOptions()
                    .placeholder(R.drawable.member_pic))
                .into(imageView)

            //Navigates to rating fragment. Member id passed in SafeArgs.
            binding.rateButton.setOnClickListener(){
                this.findNavController().navigate(MemberFragmentDirections.actionMemberFragmentToRatingFragment(memberID))
            }
        })

        return binding.root
    }
}