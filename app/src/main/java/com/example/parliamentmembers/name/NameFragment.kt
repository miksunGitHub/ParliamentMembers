package com.example.parliamentmembers.name

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databinding.FragmentNameBinding
import com.example.parliamentmembers.utilities.InjectorUtils

//Mikko Suhonen
//Student ID: 2012950
//Date: 11.10.2021
//
//In the fragment the user can find a member of parliament by giving a name to an edit text view.
//If the name matches an object in the database, navigates to the member fragment.

class NameFragment : Fragment() {

    private lateinit var binding: FragmentNameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding=DataBindingUtil.inflate(
            inflater, R.layout.fragment_name, container,false
        )

        binding.lifecycleOwner = this

        //The parliament logo is displayed using Glide
        val logoView = binding.logoView
        val imgUrl="https://users.metropolia.fi/~a16002/pngs/eduskunta.png"
        val imageUri=imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(logoView.context)
                .load(imageUri)
                .override(Target.SIZE_ORIGINAL, 200)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.member_pic))
                .into(logoView)

        binding.findButton.setOnClickListener{

            val editText=binding.editTextTextPersonName

            //Hide the keyboard
            //https://www.codegrepper.com/code-examples/kotlin/hide+keyboard+in+fragment+android+kotlin
            val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)

            val name: String=editText.text.toString()

            //Splits the name given to a list
            val nameList=name.split(" ")

            //checks that the format of the name ic correct
            if(nameList.size==2) {

                val firstName: String = nameList[0]

                val lastName: String = nameList[1]

                //View model factory created with name strings given as parameters
                val factory= InjectorUtils.nameViewModelFactory(firstName, lastName)

                val viewModel= ViewModelProviders.of(this, factory)
                        .get(NameViewModel::class.java)

                //Observes the live data object defined in the view model, matching the name
                viewModel.member.observe(viewLifecycleOwner, Observer {

                    //If no object is returned, asks user to check the typing
                    if (it==null){
                        Toast.makeText(context, "Please check the spelling", Toast.LENGTH_LONG).show()


                    }else{
                        //If the search is done several times, the list needs to be initialized
                        //for the next search
                        nameList.isEmpty()

                        //Navigates to member fragment, passes the id of parliament member object to
                        //it in safeArgs
                        this.findNavController().navigate(NameFragmentDirections.actionNameFragmentToMemberFragment(it.id))
                    }
                })
            //If the name does not include both first and last name, the user is asked to try again
            }else{Toast.makeText(context, "Please check the spelling", Toast.LENGTH_LONG).show()}

        }

        return binding.root
    }



}