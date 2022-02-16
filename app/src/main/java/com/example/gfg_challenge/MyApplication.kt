package com.example.gfg_challenge

import android.app.Application
import com.example.gfg_challenge.network.APIServices
import com.example.gfg_challenge.network.RetrofitInst

class MyApplication : Application() {
    lateinit var mainRepository: MainRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    fun initialize() {
        val quotesAPI = RetrofitInst.getRetrofitInstance().create(APIServices::class.java)
        mainRepository = MainRepository(quotesAPI)
    }
}