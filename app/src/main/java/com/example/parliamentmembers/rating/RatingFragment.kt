package com.example.parliamentmembers.rating

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.parliamentmembers.Functions
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databaseAndNetwork.ParliamentMember
import com.example.parliamentmembers.databinding.FragmentRatingBinding
import com.example.parliamentmembers.member.MemberFragmentArgs
import com.example.parliamentmembers.utilities.InjectorUtils

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//Fragment, where the user can store a numeric 1 to 5 rating and a short review text, which are
//displayed in the member fragment.

class RatingFragment : Fragment(), Functions {

    private lateinit var binding: FragmentRatingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_rating, container, false)

        binding.lifecycleOwner = this

        //Gets the member ID from the safeArgs.
        val memberFragmentArgs= MemberFragmentArgs.fromBundle(requireArguments())
        val memberID=memberFragmentArgs.memberID

        //Creates a view model factory passing the member Id as a constructor parameter
        val factory=InjectorUtils.ratingViewModelFactory(memberID)

        val viewModel= ViewModelProviders.of(this, factory)
            .get(RatingViewModel::class.java)

        binding.saveButton.setOnClickListener {
            viewModel.getMemberByID().observe(viewLifecycleOwner, Observer {

                val previousReviews=it.review

                val currentReview=binding.rateMemberEditText.text.toString()

                //If a review already exists in the database, the new review is added to it
                //with 2 new line characters separating the reviews.
                val review=concatenateReviews(previousReviews, currentReview)

                val previousRating=it.rating

                //Checks if there is a previous entry in the database
                val rating=if(previousRating!=0f) {previousRating
                }else{binding.ratingBar.rating}

                //parliment member object with the given rating and review attached
                val updatedParliamentMember = ParliamentMember( memberID, it.member_num,
                        it.first_name, it.last_name, it.age, it.party, it.constituency,
                        it.minister, rating, review, it.picture)

                //View model function used to update the data in the database
                viewModel.updateMember(updatedParliamentMember)

                val editText=binding.rateMemberEditText

                //Hide the keyboard
                //https://www.codegrepper.com/code-examples/kotlin/hide+keyboard+in+fragment+android+kotlin
                val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)

                //Navigates back to the member fragment passing member Id in the safeArgs.
                this.findNavController().navigate(RatingFragmentDirections.actionRatingFragmentToMemberFragment(memberID))
            })
        }

        return binding.root
    }
}