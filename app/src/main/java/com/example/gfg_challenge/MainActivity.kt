package com.example.gfg_challenge

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gfg_challenge.adapter.NewsListAdapter
import com.example.gfg_challenge.model.Items
import com.example.gfg_challenge.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var list: ArrayList<Items> = ArrayList()
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val repository = (application as MyApplication).mainRepository
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        swipeContainer = findViewById(R.id.swipeContainer)

        mainViewModel =
            ViewModelProvider(
                this,
                MyViewModelFactory(this, repository)
            ).get(MainViewModel::class.java)
        val newsListAdapter = NewsListAdapter(this, list)

        mainViewModel.mainData.observe(this, Observer {
            Log.i("MAINTAGRESP", "Response  ${it.toString()}")
            if (it != null) {
                list = it.items
                newsListAdapter.updateList(list)
            }
            swipeContainer.isRefreshing = false
        })

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsListAdapter
        }

        swipeContainer.setOnRefreshListener {
            mainViewModel.makeAPICall()
        }

        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

    }

}