package com.example.parliamentmembers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

        viewModel.parliamentMemberDataByID.observe(viewLifecycleOwner, Observer{
            binding.partyMemberFirstName.setText(it.first().first_name)
            binding.partyMemberLastName.setText(it.first().last_name)

            //https://stackoverflow.com/questions/136419/get-integer-value-of-the-current-year-in-java
            var currentYear= Calendar.getInstance().get(Calendar.YEAR)
            binding.ageView.setText((currentYear-it.first().age).toString())

            binding.partyNameView.setText(it.first().party)
            binding.constituencyView.setText(it.first().constituency)
        })

        return binding.root
    }
}