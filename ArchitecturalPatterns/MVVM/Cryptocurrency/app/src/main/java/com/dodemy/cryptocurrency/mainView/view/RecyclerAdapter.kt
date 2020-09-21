package com.dodemy.cryptocurrency.mainView.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.cryptocurrency.R
import com.dodemy.cryptocurrency.mainView.model.CoinModel
import com.dodemy.cryptocurrency.helpers.inflate
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val coins: List<CoinModel>) : RecyclerView.Adapter<RecyclerAdapter.CoinHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.CoinHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return CoinHolder(inflatedView)
    }

    override fun getItemCount() = coins.size

    override fun onBindViewHolder(holder: RecyclerAdapter.CoinHolder, position: Int) {
        val itemPhoto = coins[position]
        holder.bindCoin(itemPhoto)
    }

    class CoinHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var coin: CoinModel? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
        }

        fun bindCoin(coin: CoinModel) {
            this.coin = coin

            view.coinName.text = coin.name
            view.coinPrice.text = coin.priceUsd
        }
    }
}