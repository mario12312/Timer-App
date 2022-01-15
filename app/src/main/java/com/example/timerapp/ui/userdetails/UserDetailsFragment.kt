package com.example.timerapp.ui.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.timerapp.R
import com.example.timerapp.databinding.UserDetailsFragmentBinding
import com.example.timerapp.model.User

class UserDetailsFragment : Fragment(R.layout.user_details_fragment) {

    private val args: UserDetailsFragmentArgs by navArgs()

    lateinit var binding: UserDetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UserDetailsFragmentBinding.bind(view)
        if(!args.equals(null)){
            val user: User = args.userdetailsArg!!

            binding.txtUserid.setText("User ID: " + user.userId.toString())
            binding.txtId.setText("ID: " + user.id.toString())
            binding.txtTitle.setText("Title: " + user.title)
            binding.txtCompleted.setText("Completed: " + user.completed.toString())
        }
    }


}