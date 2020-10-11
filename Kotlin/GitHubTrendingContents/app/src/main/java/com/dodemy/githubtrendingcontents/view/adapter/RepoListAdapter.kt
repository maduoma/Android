package com.dodemy.githubtrendingcontents.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.dodemy.githubtrendingcontents.databinding.ViewRepoListItemBinding
import com.dodemy.githubtrendingcontents.model.Item
import com.dodemy.githubtrendingcontents.view.adapter.viewHolders.RepoListViewHolder
import com.dodemy.githubtrendingcontents.view.ui.repolist.RepoListViewModel

class RepoListAdapter(private val repoListViewModel: RepoListViewModel) : RecyclerView.Adapter<RepoListViewHolder>() {
     var repoList: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewRepoListItemBinding.inflate(inflater, parent, false)
        return RepoListViewHolder(dataBinding, repoListViewModel)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.setup(repoList[position])
    }

    fun updateRepoList(repoList: List<Item>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}