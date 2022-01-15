package com.example.timerapp.ui.users

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.timerapp.R
import com.example.timerapp.databinding.UsersFragmentBinding
import com.example.timerapp.model.User
import com.example.timerapp.utils.AppUtil
import com.example.timerapp.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.users_fragment), RecyclerViewAdapter.OnUserClick {
    companion object {
        fun newInstance() = UsersFragment()
    }
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: UsersViewModel
    lateinit var binding: UsersFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UsersFragmentBinding.bind(view)
        initRecyclerView()
        val swipe = binding.swipeRefreshLayout
        initViewModel(false, swipe)

        swipe.setOnRefreshListener {
            initViewModel(true, swipe)
        }

        recyclerViewAdapter.onItemClick = {
            userdetails ->
            findNavController().navigate(
                R.id.UserDetailsFragment,
                bundleOf(
                    "userdetailsArg" to userdetails
                ),
                AppUtil.getNavOptions()
            )
        }
    }

    private fun initRecyclerView(){
        binding.rvUserslist.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter = RecyclerViewAdapter(requireContext(),this@UsersFragment)
        binding.rvUserslist.adapter = recyclerViewAdapter
    }

    private fun initViewModel(refreshing: Boolean, swipeRefreshLayout: SwipeRefreshLayout){
        if(refreshing){
            swipeRefreshLayout.isRefreshing = false
        }
        val viewModel =
            ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null){
                recyclerViewAdapter.setlistData(it)
                recyclerViewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(requireContext(), "Error in getting data.", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.loadListOfUsers()
    }

    override fun onItemClick(list: List<User>, position: Int) {
    }
}