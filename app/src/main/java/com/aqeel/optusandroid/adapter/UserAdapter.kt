package com.aqeel.optusandroid.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aqeel.optusandroid.databinding.ItemUserinfoBinding
import com.aqeel.optusandroid.model.UserInfo

class UserAdapter(
    private val context: Context,
    private val userInfoList: ArrayList<UserInfo>,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(private val userinfoBinding: ItemUserinfoBinding) :
        RecyclerView.ViewHolder(userinfoBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(
            context: Context,
            userInfo: UserInfo,
            onClickListener: (Int) -> Unit,
            position: Int
        ) {
            userinfoBinding.userinfoId.text = "ID: ${userInfo.id}"
            userinfoBinding.userinfoName.text = "Name: ${userInfo.name}"
            userinfoBinding.userinfoEmail.text = "Email: ${userInfo.email}"
            userinfoBinding.userinfoPhone.text = "Phone: ${userInfo.phone}"

            userinfoBinding.cardView.setOnClickListener { onClickListener(position) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userinfoBinding =
            ItemUserinfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(userinfoBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, userInfoList[position], onClickListener, position)

    }

    override fun getItemCount(): Int = userInfoList.size
}