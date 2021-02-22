package com.dodemy.productswitharchitecturecomponents.features.productslist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.productswitharchitecturecomponents.R
import com.dodemy.productswitharchitecturecomponents.repository.model.Product
import com.dodemy.productswitharchitecturecomponents.repository.model.ProductDiffUtilCallback

class ProductsAdapter : RecyclerView.Adapter<ProductsItemViewHolder>() {

    private val products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductsItemViewHolder(parent, R.layout.layout_products_item)

    override fun onBindViewHolder(holder: ProductsItemViewHolder, position: Int) {
        holder.render(products[position])
    }

    override fun getItemCount() = products.size

    override fun getItemViewType(position: Int) = position

    fun updateProducts(products: List<Product>) {
        val diffResult = DiffUtil.calculateDiff(ProductDiffUtilCallback(this.products, products))
        diffResult.dispatchUpdatesTo(this)
        this.products.clear()
        this.products.addAll(products)
    }
}