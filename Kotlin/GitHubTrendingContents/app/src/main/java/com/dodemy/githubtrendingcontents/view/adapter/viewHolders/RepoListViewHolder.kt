package com.dodemy.githubtrendingcontents.view.adapter.viewHolders

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.githubtrendingcontents.BR
import com.dodemy.githubtrendingcontents.R
import com.dodemy.githubtrendingcontents.model.Item
import com.dodemy.githubtrendingcontents.view.ui.repolist.RepoListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_repo_list_item.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.sdk27.coroutines.onClick


class RepoListViewHolder constructor(private val dataBinding: ViewDataBinding, private val repoListViewModel: RepoListViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {

    private val avatarImage = itemView.item_avatar
    fun setup(itemData: Item) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

        Picasso.get().load(itemData.owner.avatar_url).into(avatarImage);

        itemView.onClick {
            //val bundle = bundleOf("url" to itemData.html_url)
            val bundle = bundleOf("url" to itemData.html_url)
            itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment, bundle)
        }
    }
}