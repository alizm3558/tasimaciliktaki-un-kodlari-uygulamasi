package com.example.un

import retrofit2.Call
import retrofit2.http.GET

interface DataApi {

    @GET("UNkodlari")
    fun getData():Call<List<DataModel>>
}