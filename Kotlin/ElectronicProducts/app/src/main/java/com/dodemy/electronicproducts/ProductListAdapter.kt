package com.dodemy.electronicproducts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 
import androidx.recyclerview.widget.RecyclerView

class ProductListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        product: ProductListPOJODataClassesDataItem,
        clickListener: ProductListOnItemClickListener
    ) {

        //This is for showing the information in each recyclerview item
        productName.text = product.proName
        productPrice.text = product.proSaleprice.toString()
        productStockQuantity.text = product.stkAllqty.toString()

        //And these are for the detail popup dialog content
        detailProductName.text = product.proName
        detailProductPrice.text = product.proSaleprice.toString()
        detailProductLastUpdate.text = product.sktLastupdate
        detailProductStockQty.text = product.stkAllqty.toString()

        itemView.setOnClickListener {
            clickListener.onItemClicked(product)
        }
    }
}


class ProductListAdapter(
    private var productList: ProductListPOJODataClasses,
    private val itemClickListener: ProductListOnItemClickListener
) : RecyclerView.Adapter<ProductListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListItemHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item_layout, parent, false)

        return ProductListItemHolder(v)
    }

    override fun getItemCount(): Int {
        return productList.data!!.size
    }

    override fun onBindViewHolder(holder: ProductListItemHolder, position: Int) {
        val product = productList.data?.get(position)
        if (product != null) {
            holder.bind(product, itemClickListener)
        }
    }
}


interface ProductListOnItemClickListener {
    fun onItemClicked(product: ProductListPOJODataClassesDataItem)
}