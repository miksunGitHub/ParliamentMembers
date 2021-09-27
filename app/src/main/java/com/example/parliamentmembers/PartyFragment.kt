package com.example.parliamentmembers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMemberDataBase
import com.example.parliamentmembers.databinding.FragmentPartyBinding

class PartyFragment : Fragment() {

    private lateinit var viewModel: PartyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPartyBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party, container, false)

        binding.setLifecycleOwner (this)

        val application = requireNotNull(this.activity).application

        val dataSource = ParliamentMemberDataBase.getInstance().parliamentMemberDao

        val viewModelFactory=PartyViewModelFactory(dataSource,application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PartyViewModel::class.java)

        binding.partyViewModel= viewModel

        binding.testButton.setOnClickListener(){

            viewModel.onClick()
        }

        return binding.root
    }


}