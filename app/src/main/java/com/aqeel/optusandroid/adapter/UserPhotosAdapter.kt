package com.aqeel.optusandroid.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aqeel.optusandroid.databinding.ItemUserphototsBinding
import com.aqeel.optusandroid.model.UserPhotosResponse
import com.squareup.picasso.Picasso

class UserPhotosAdapter(
    private val context: Context,
    private val userPhotosList: ArrayList<UserPhotosResponse>,
    private val onClickListener: (UserPhotosResponse) -> Unit
) : RecyclerView.Adapter<UserPhotosAdapter.ViewHolder>() {
    class ViewHolder(private val userPhotoBinding: ItemUserphototsBinding) :
        RecyclerView.ViewHolder(userPhotoBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(
            context: Context,
            userPhotosResponse: UserPhotosResponse,
            onClickListener: (UserPhotosResponse) -> Unit,
        ) {
            userPhotoBinding.userphotoText.text = "${userPhotosResponse.title}"
            Picasso.get().load(userPhotosResponse.thumbnailUrl)
                .into(userPhotoBinding.userphotoPhoto)

            userPhotoBinding.cardView.setOnClickListener {
                onClickListener(userPhotosResponse)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userPhotoBinding =
            ItemUserphototsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(userPhotoBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, userPhotosList[position], onClickListener)

    }

    override fun getItemCount(): Int = userPhotosList.size

}