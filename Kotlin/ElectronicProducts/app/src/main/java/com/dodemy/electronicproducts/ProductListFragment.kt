package com.dodemy.electronicproducts

import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

public class ProductListFragment {
    override fun onItemClicked(product: ProductListPOJODataClassesDataItem) {
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from((activity as AppCompatActivity)).inflate(
            R.layout.product_list_dialog_layout, null
        )
        val detailProductName: TextView = mDialogView.findViewById(R.id.detailProductName)
        val detailProductPrice: TextView = mDialogView.findViewById(R.id.detailProductPrice)
        val detailProductLastUpdate: TextView = mDialogView.findViewById(R.id.detailProductLastUpdate)
        val detailProductStockQty: TextView = mDialogView.findViewById(R.id.detailProductStockQty)

        detailProductName.text = product.proName
        detailProductPrice.text = product.proSaleprice.toString()
        detailProductLastUpdate.text = product.sktLastupdate
        detailProductStockQty.text = product.stkAllqty.toString()




        val selectedStkOutcode = product.stkOutcode
        val selectedProcode = product.stkProdcode

        if (selectedStkOutcode != null && selectedProcode != null) {

            // These line below show the information i desired in the log.

            Log.i("Product", "Clicked product name: ${product.proName}")
            Log.i("Product", "Clicked product price: ${product.proSaleprice}")
            Log.i("Product", "Clicked product stock qty: ${product.stkAllqty}")
            Log.i("Product", "Clicked product last update: ${product.sktLastupdate}")
        }

        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder((activity as AppCompatActivity))
            .setView(mDialogView)
            .setTitle("Product X")
        //show dialog
        val mAlertDialog = mBuilder.show()

        mDialogView.closeButton.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }
}
