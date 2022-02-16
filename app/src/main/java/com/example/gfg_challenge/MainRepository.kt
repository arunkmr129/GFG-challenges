package com.example.gfg_challenge

import com.example.gfg_challenge.network.APIServices

class MainRepository(private val retrofitService: APIServices) {
    fun getAllData() = retrofitService.getAllData()
}