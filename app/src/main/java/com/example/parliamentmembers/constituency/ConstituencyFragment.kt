package com.example.parliamentmembers.constituency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databinding.FragmentConstituencyBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Class controls the constituency fragment, that can be chosen in the bottom navigation bar of the
//starting fragment. It sets the on click listeners to constituency names in the scroll view, and
//defines the transition to constituency member fragment, where the PMs from the chosen constituency
//are shown.

class ConstituencyFragment : Fragment() {

    private lateinit var binding: FragmentConstituencyBinding

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =DataBindingUtil.inflate(
                inflater, R.layout.fragment_constituency, container, false)

        binding.lifecycleOwner = this

        //Bottom navigation bar created
        bottomNavigationView=binding.bottomNavigationView
        val navController=this.findNavController()
        bottomNavigationView.setupWithNavController(navController)

        //Transition to next fragment. The name of the constituency is on passed using safeArgs.
        fun moveToNextView(constituency: String){
            navController.navigate(ConstituencyFragmentDirections.actionConstituencyFragmentToConstituencyMembersFragment(constituency))
        }

        //On click listeners for the constituency text views.
        binding.centralTextView.setOnClickListener {moveToNextView("Keski-Suomi") }
        binding.hameTextView.setOnClickListener { moveToNextView("Häme") }
        binding.helsinkiTextView.setOnClickListener { moveToNextView("Helsinki") }
        binding.laplandTextView.setOnClickListener { moveToNextView("Lappi") }
        binding.ouluTextView.setOnClickListener { moveToNextView("Lappi") }
        binding.pirkanmaaView.setOnClickListener { moveToNextView("Pirkanmaa") }
        binding.savokareliaTextView.setOnClickListener { moveToNextView("Savo-Karjala") }
        binding.southeastTextView.setOnClickListener { moveToNextView("Kaakkois-Suomi") }
        binding.alandTextView.setOnClickListener { moveToNextView("Ahvenanmaa") }
        binding.uusimaaTextView.setOnClickListener { moveToNextView("Uusimaa") }
        binding.vaasaTextView.setOnClickListener { moveToNextView("Vaasa") }
        binding.varsinaisTextView.setOnClickListener { moveToNextView("Varsinais-Suomi") }
        binding.satakuntaTextView.setOnClickListener { moveToNextView("Satakunta") }

        return binding.root
    }

}