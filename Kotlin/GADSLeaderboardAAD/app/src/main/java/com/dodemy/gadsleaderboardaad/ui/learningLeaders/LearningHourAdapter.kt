package com.dodemy.gadsleaderboardaad.ui.learningLeaders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.gadsleaderboardaad.databinding.RecyclerViewItemBinding
import com.dodemy.gadsleaderboardaad.model.Hours

class LearningHourAdapter : ListAdapter<Hours, LearningHourAdapter.HoursViewHolder>(DiffUtil()) {
    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Hours>() {
        override fun areItemsTheSame(oldItem: Hours, newItem: Hours): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hours, newItem: Hours): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        return HoursViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        val hour = getItem(position)
        holder.bind(hour)
    }


    class HoursViewHolder(private val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hours: Hours) {
            binding.hour = hours
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HoursViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerViewItemBinding.inflate(layoutInflater, parent, false)
                return HoursViewHolder(binding)
            }
        }
    }
}