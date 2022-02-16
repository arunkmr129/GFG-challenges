package com.example.gfg_challenge

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gfg_challenge.viewModel.MainViewModel

class MyViewModelFactory(private val context: Context, private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(context,repository) as T
    }
}