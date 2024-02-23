package com.example.sampledatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sampledatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { MainViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.tasks.observe(this,) {
            val recyclerView = findViewById<RecyclerView>(R.id.task_list)
            recyclerView.adapter = ItemAdapter(this, it)
        }
    }
}