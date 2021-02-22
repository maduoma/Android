package com.dodemy.productswitharchitecturecomponents.repository.model

import androidx.recyclerview.widget.DiffUtil


class ProductDiffUtilCallback(private val oldProducts: List<Product>, private val newProducts: List<Product>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldProducts.size

    override fun getNewListSize() = newProducts.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldProducts[oldItemPosition].id == newProducts[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldProducts[oldItemPosition] == newProducts[newItemPosition]
}