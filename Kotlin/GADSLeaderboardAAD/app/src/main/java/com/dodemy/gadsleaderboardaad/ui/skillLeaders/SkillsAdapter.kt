package com.dodemy.gadsleaderboardaad.ui.skillLeaders


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.gadsleaderboardaad.databinding.RecyclerViewItem1Binding
import com.dodemy.gadsleaderboardaad.model.Skills

class SkillsAdapter : ListAdapter<Skills, SkillsAdapter.SkillAdapterViewHolder>(DiffUtil()) {

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Skills>() {
        override fun areItemsTheSame(oldItem: Skills, newItem: Skills): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Skills, newItem: Skills): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillAdapterViewHolder {
        return SkillAdapterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SkillAdapterViewHolder, position: Int) {
        val skill = getItem(position)
        holder.bind(skill)
    }


    class SkillAdapterViewHolder(private val binding: RecyclerViewItem1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(skill: Skills) {
            binding.skills = skill
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SkillAdapterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerViewItem1Binding.inflate(layoutInflater, parent, false)
                return SkillAdapterViewHolder(binding)
            }
        }

    }
}
