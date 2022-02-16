package com.example.gfg_challenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInst {

    companion object {
        private val BASE_URL =
            "https://api.rss2json.com/v1/" //api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml
        private var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}