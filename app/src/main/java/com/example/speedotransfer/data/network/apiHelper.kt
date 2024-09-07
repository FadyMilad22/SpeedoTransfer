package com.example.speedotransfer.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiHelper {




        val gson: Gson = GsonBuilder().serializeNulls().create()
         private   val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("/*ToDO add the Base URL*/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()


     val callable: apiHelper by lazy {
        retrofit.create(apiHelper::class.java)


    }

    }
