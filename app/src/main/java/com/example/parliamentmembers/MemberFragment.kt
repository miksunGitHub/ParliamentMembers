package com.example.parliamentmembers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.parliamentmembers.databaseAndNetwork.Repository
import com.example.parliamentmembers.databinding.FragmentMemberBinding
import com.example.parliamentmembers.utilities.InjectorUtils
import java.time.LocalDateTime
import java.util.*

class MemberFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding:FragmentMemberBinding=DataBindingUtil.inflate(
            inflater, R.layout.fragment_member, container,false)



        binding.setLifecycleOwner(this)

        val memberFragmentArgs=MemberFragmentArgs.fromBundle(requireArguments())

        var memberID=memberFragmentArgs.memberID

        val factory=InjectorUtils.memberViewModelFactory(memberID)

        val viewModel= ViewModelProviders.of(this, factory)
            .get(MemberViewModel::class.java)

        binding.memberViewModel= viewModel

        //viewModel.storeImgCaches(binding.memberFaceView)



        viewModel.parliamentMemberDataByID.observe(viewLifecycleOwner, Observer{
            binding.partyMemberFirstName.setText(it.first().first_name)
            binding.partyMemberLastName.setText(it.first().last_name)

            //https://stackoverflow.com/questions/136419/get-integer-value-of-the-current-year-in-java
            var currentYear= Calendar.getInstance().get(Calendar.YEAR)
            binding.ageView.setText((currentYear-it.first().age).toString())

            binding.partyNameView.setText(it.first().party)
            binding.constituencyView.setText(it.first().constituency)

            var rating=it.first().rating

            if(rating>0.0){binding.ratingBar.setRating(rating)}

            var imageView=binding.memberFaceView

            var imgUrl="https://avoindata.eduskunta.fi/"+it.first().picture

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