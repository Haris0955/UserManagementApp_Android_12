package com.example.usermanagementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val users : List<UserItem>,private val userManagement: UserManagement) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    class UserViewHolder (userView: View) : RecyclerView.ViewHolder(userView){

        val userImgIV: ImageView = userView.findViewById(R.id.idUserImg)
        val userNameTV: TextView = userView.findViewById(R.id.idUserName)
        val userBioTV: TextView = userView.findViewById(R.id.idUserBio)
        val addBtn: Button= userView.findViewById(R.id.idAddButton)
        val deleteBtn: Button= userView.findViewById(R.id.idDeleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }



    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = users[position]
        holder.userImgIV.setImageResource(currentItem.userImg)
        holder.userBioTV.text = currentItem.userBio
        holder.userNameTV.text = currentItem.userName
        holder.addBtn.setOnClickListener {
            userManagement.add(currentItem.id)
        }

        holder.deleteBtn.setOnClickListener {
            userManagement.delete(currentItem.id)
        }

    }
    override fun getItemCount()=users.size
}