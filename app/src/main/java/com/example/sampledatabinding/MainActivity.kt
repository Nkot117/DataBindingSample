package com.example.sampledatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sampledatabinding.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { MainViewModel() }
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.taskList.adapter = ItemAdapter(this, viewModel).also {
            itemAdapter = it
        }

        viewModel.tasks.observe(this) {
            itemAdapter.submitList(it)
        }

        viewModel.selectedTask.observe(this) {
            showSnackBar(it.name)
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, "${message}がクリックされた", Snackbar.LENGTH_SHORT).show()
    }
}