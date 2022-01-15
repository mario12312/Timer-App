package com.example.timerapp.network.users

import com.example.timerapp.model.User
import retrofit2.Call
import retrofit2.http.GET

interface RetroServieInstance {

    @GET("todos")
    fun getUsers(): Call<List<User>>
}