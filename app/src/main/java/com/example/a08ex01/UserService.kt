package com.example.a08ex01

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{user}")
    fun getUser(@Path("user") user: String): Call<UserResponse>
}