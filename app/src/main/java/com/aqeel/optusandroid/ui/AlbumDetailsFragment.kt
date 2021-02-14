package com.aqeel.optusandroid.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aqeel.optusandroid.databinding.FragmentAlbumDetailsBinding
import com.aqeel.optusandroid.model.UserPhotosResponse
import com.squareup.picasso.Picasso

class AlbumDetailsFragment : Fragment() {
    private lateinit var albumDetailsBinding: FragmentAlbumDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        albumDetailsBinding = FragmentAlbumDetailsBinding.inflate(
            inflater, container, false
        )

        return albumDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPhotoResponse = arguments?.getParcelable<UserPhotosResponse>("userPhotosResponse")
        setView(userPhotoResponse!!)
    }

    @SuppressLint("SetTextI18n")
    private fun setView(userPhotoResponse: UserPhotosResponse) {
        albumDetailsBinding.useralbumId.text = "Album ID: ${userPhotoResponse.albumId.toString()}"
        albumDetailsBinding.useralbumPhotoId.text = "Photo ID: ${userPhotoResponse.id.toString()}"
        albumDetailsBinding.useralbumText.text = userPhotoResponse.title.toString()
        Picasso.get().load(userPhotoResponse.url).into(albumDetailsBinding.useralbumPhoto)
    }
}