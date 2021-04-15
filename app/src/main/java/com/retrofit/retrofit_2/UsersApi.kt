package com.retrofit.retrofit_2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("api/users")

    fun getUsersList(@Query("page") page :Int) : Call<UsersModelList.UsersListModel>
}

object UserRetrofitService{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}