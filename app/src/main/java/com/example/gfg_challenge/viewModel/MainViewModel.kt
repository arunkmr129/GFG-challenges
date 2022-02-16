package com.example.gfg_challenge.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gfg_challenge.MainRepository
import com.example.gfg_challenge.model.MainDataModel
import com.example.gfg_challenge.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(context: Context, private val repository: MainRepository) :
    ViewModel() {

    val mainData = MutableLiveData<MainDataModel>()
    val errorMessage = MutableLiveData<String>()

    init {
        if (NetworkUtils.checkConnection(context)) {
            makeAPICall()
        }
    }

    fun makeAPICall() {
        val response = repository.getAllData()
        response.enqueue(object : Callback<MainDataModel> {
            override fun onResponse(call: Call<MainDataModel>, response: Response<MainDataModel>) {
                mainData.postValue(response.body())
            }

            override fun onFailure(call: Call<MainDataModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}