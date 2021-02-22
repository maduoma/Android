

package com.dodemy.productswitharchitecturecomponents.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dodemy.productswitharchitecturecomponents.utilities.*
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
        @PrimaryKey @SerializedName(KEY_PRODUCT_ID) val id: String,
        @SerializedName(KEY_PRODUCT_BRAND) var brand: String?,
        @SerializedName(KEY_PRODUCT_NAME) val name: String?,
        @SerializedName(KEY_PRODUCT_SIZE) var size: String?,
        @SerializedName(KEY_PRODUCT_IMAGES) val images: List<ProductImage>
)

@Entity
data class ProductImage(
        @PrimaryKey @SerializedName(KEY_PRODUCT_IMAGE_URL) val url: String
)