package com.dodemy.productswitharchitecturecomponents.repository.model

import androidx.room.TypeConverter
import com.dodemy.productswitharchitecturecomponents.utilities.COMMA
import com.dodemy.productswitharchitecturecomponents.utilities.EMPTY

class ProductImageListTypeConverter {

    @TypeConverter
    fun fromListToString(list: List<ProductImage>): String {
        var result = EMPTY
        list.forEach { result += "${it.url}$COMMA" }
        return result.substringBeforeLast(COMMA)
    }

    @TypeConverter
    fun fromStringToList(string: String): List<ProductImage> {
        val result = mutableListOf<ProductImage>()
        //!string.isBlank()
        if (string.isNotBlank()) {
            string.split(COMMA).forEach { result += ProductImage(it) }
        }

        return result
    }
}