package com.aqeel.optusandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqeel.optusandroid.R
import com.aqeel.optusandroid.adapter.UserAdapter
import com.aqeel.optusandroid.databinding.FragmentUserBinding
import com.aqeel.optusandroid.model.UserInfo
import com.aqeel.optusandroid.viewmodel.UserViewModel
import com.aqeel.optusandroid.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UserFragment : Fragment() {

    lateinit var userFragmentBinding: FragmentUserBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userFragmentBinding = FragmentUserBinding.inflate(inflater, container, false)
        return userFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeader()
        initRecyclerView()
        getUserData()
    }

    private fun initRecyclerView() {
        userFragmentBinding.recyclerviewFirst.layoutManager = LinearLayoutManager(activity)
    }

    private fun setHeader() {
        userFragmentBinding.headerText.text = resources.getString(R.string.userinfo_first_fragment)
    }

    private fun getUserData() {
        userViewModel.getUserData().observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { usersData ->
                        Timber.d("UserData $it")
                        userFragmentBinding.progressbar.visibility = View.GONE
                        setUserData(usersData)
                    }
                }
                Status.LOADING -> {
                    userFragmentBinding.progressbar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    userFragmentBinding.progressbar.visibility = View.GONE
                    Timber.d("Errorrr - ${it.data}")
                }
            }
        })
    }

    private fun setUserData(usersData: List<UserInfo>) {
        val adapter = UserAdapter(requireContext(), usersData as ArrayList<UserInfo>) {
            navigateToPhotosFragment(it)
        }
        userFragmentBinding.recyclerviewFirst.adapter = adapter
    }

    private fun navigateToPhotosFragment(position: Int) {
        val bundle = bundleOf("selected_id" to position + 1)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

}