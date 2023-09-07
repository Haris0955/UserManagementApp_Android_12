package com.example.usermanagementapp

data class UserItem(
    val id: Int,
    val userName: String,
    val userBio: String,
    val userImg: Int,
    var userExist: Boolean
)
