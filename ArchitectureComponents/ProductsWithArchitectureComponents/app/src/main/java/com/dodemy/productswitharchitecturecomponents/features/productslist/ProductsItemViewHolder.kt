

package com.dodemy.productswitharchitecturecomponents.features.productslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.productswitharchitecturecomponents.R
import com.dodemy.productswitharchitecturecomponents.repository.model.Product
import com.dodemy.productswitharchitecturecomponents.repository.model.ProductImage
import com.dodemy.productswitharchitecturecomponents.utilities.DASH
import com.dodemy.productswitharchitecturecomponents.utilities.EMPTY
import com.dodemy.productswitharchitecturecomponents.utilities.UNAVAILABLE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_products_item.view.*

class ProductsItemViewHolder(
        parent: ViewGroup,
        @LayoutRes itemViewLayoutId: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(itemViewLayoutId, parent, false)) {

    fun render(product: Product) {
        renderProductImage(product.images)
        renderProductName(product.name ?: DASH)
        renderProductBrand(product.brand ?: EMPTY)
        renderProductSize(product.size ?: EMPTY)
    }

    private fun renderProductImage(images: List<ProductImage>) {
        //!images.isEmpty()
        if (images.isNotEmpty())
            Picasso.get()
                    .load(images[0].url)
                    .placeholder(R.drawable.background_no_image)
                    .into(itemView.productsItemImageView)
    }

    private fun renderProductName(name: String) {
        itemView.productsItemNameTextView.text = name
    }

    private fun renderProductBrand(brand: String) {
        itemView.productsItemBrandTextView.text = itemView.context.getString(R.string.product_item_brand, if (brand.isBlank()) UNAVAILABLE else brand)
    }

    private fun renderProductSize(size: String) {
        itemView.productsItemSizeTextView.text = itemView.context.getString(R.string.product_item_size, if (size.isBlank()) UNAVAILABLE else size)
    }
}