package gst.trainingcourse.roomexample.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.widget.Toast.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import gst.trainingcourse.roomexample.R
import gst.trainingcourse.roomexample.R.*
import gst.trainingcourse.roomexample.model.User
import gst.trainingcourse.roomexample.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(layout.fragment_update, container, false)
        view.edtUpdateFirstName.setText(args.currentUser.firstName)
        view.edtUpdateLastName.setText(args.currentUser.lastName)
        view.edtUpdateAge.setText(args.currentUser.age.toString())
        setHasOptionsMenu(true)

        mUserViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        view.btnUpdate.setOnClickListener {
            updateData()
        }
        return view
    }

    private fun updateData() {
        val firstName = view?.edtUpdateFirstName?.text.toString()
        val lastName = view?.edtUpdateLastName?.text.toString()
        val age = Integer.parseInt(view?.edtUpdateAge?.text.toString())
        if (inputCheck(firstName, lastName, view?.edtUpdateAge!!.text)) {
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            mUserViewModel.update(updateUser)
            makeText(context, "update success", LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            makeText(requireContext(), "please fill out all data", LENGTH_SHORT).show()
        }
    }



    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            makeText(
                context,
                "Successfully delete user : ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are you sure want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }

}