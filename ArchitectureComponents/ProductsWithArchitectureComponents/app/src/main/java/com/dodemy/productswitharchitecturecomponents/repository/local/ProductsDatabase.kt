

package com.dodemy.productswitharchitecturecomponents.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dodemy.productswitharchitecturecomponents.repository.model.Product
import com.dodemy.productswitharchitecturecomponents.repository.model.ProductImageListTypeConverter
import com.dodemy.productswitharchitecturecomponents.utilities.PRODUCTS_DATABASE_NAME

@Database(entities = [(Product::class)], version = 1, exportSchema = false)
@TypeConverters(ProductImageListTypeConverter::class)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao

    companion object {
        private var INSTANCE: ProductsDatabase? = null
        fun instance(context: Context): ProductsDatabase {
            synchronized(ProductsDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, ProductsDatabase::class.java, PRODUCTS_DATABASE_NAME).build()
                }
                return INSTANCE!!
            }
        }
    }
}