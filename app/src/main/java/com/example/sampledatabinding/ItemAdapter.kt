package com.example.sampledatabinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampledatabinding.databinding.ListItemBinding

class ItemAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: MainViewModel
) : ListAdapter<Task, ItemAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, viewLifecycleOwner: LifecycleOwner, viewModel: MainViewModel) {
            binding.also {
                it.item = task
                it.lifecycleOwner = viewLifecycleOwner
                it.viewModel = viewModel
                it.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, viewLifecycleOwner, viewModel)
    }

    object DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.name == newItem.name
        }
    }

}