package com.example.speedotransfer.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIHelper {


    val gson: Gson = GsonBuilder().serializeNulls().create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

    val callable: APIService by lazy {
        retrofit.create(APIService::class.java)


    }

}
