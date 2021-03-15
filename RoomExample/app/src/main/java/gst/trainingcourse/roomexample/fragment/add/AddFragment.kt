package gst.trainingcourse.roomexample.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import gst.trainingcourse.roomexample.R
import gst.trainingcourse.roomexample.model.User
import gst.trainingcourse.roomexample.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        view.btnAdd.setOnClickListener {
            addDataToDatabase()
        }
        return view
    }

    private fun addDataToDatabase() {
        val firstName = edtFirstName.text.toString()
        val lastName = edtLastName.text.toString()
        val age = edtAge.text

        if (inputCheck(firstName, lastName, age)) {
            //Create user Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            //Add data to Database
            mUserViewModel.addUser(user)
            makeText(requireContext(), "Successfully added", LENGTH_SHORT).show()
            //Navigation Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            makeText(requireContext(), "Please fill out all fields", LENGTH_SHORT)
                .show()
        }
    }



    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}