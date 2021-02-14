package com.aqeel.optusandroid.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqeel.optusandroid.R
import com.aqeel.optusandroid.adapter.UserPhotosAdapter
import com.aqeel.optusandroid.databinding.FragmentUserBinding
import com.aqeel.optusandroid.model.UserInfo
import com.aqeel.optusandroid.model.UserPhotosResponse
import com.aqeel.optusandroid.utils.Status
import com.aqeel.optusandroid.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SecondFragment : Fragment() {
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
        val id = arguments?.getInt("selected_id")
        setHeader(id!!)
        initRecyclerView()
        getUserAlbumData()
    }

    private fun initRecyclerView() {
        userFragmentBinding.recyclerviewFirst.layoutManager = LinearLayoutManager(activity)
    }

    @SuppressLint("SetTextI18n")
    private fun setHeader(id: Int) {
        userFragmentBinding.headerText.text = "Album ID: $id"
    }

    private fun getUserAlbumData() {
        userViewModel.getUserAlbumData().observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { usersAlbumData ->
                        Timber.d("usersAlbumData $it")
                        userFragmentBinding.progressbar.visibility = View.GONE
                        setUserAlbumData(usersAlbumData)
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

    private fun setUserAlbumData(usersAlbumData: List<UserPhotosResponse>) {
        val adapter =
            UserPhotosAdapter(requireContext(), usersAlbumData as ArrayList<UserPhotosResponse>) {
                navigateToPhotosDetailsFragment(it)
            }
        userFragmentBinding.recyclerviewFirst.adapter = adapter
    }

    private fun navigateToPhotosDetailsFragment(userPhotosResponse: UserPhotosResponse) {
        val bundle = Bundle()
        bundle.putParcelable("userPhotosResponse", userPhotosResponse)
        findNavController()
            .navigate(R.id.action_SecondFragment_to_albumDetailsFragment, bundle)
    }
}