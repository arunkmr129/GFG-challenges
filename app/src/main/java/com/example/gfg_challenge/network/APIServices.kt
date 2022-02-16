package com.example.gfg_challenge.network

import com.example.gfg_challenge.model.MainDataModel
import retrofit2.Call
import retrofit2.http.GET

interface APIServices {
    @GET("api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml")
    fun getAllData(): Call<MainDataModel>
}