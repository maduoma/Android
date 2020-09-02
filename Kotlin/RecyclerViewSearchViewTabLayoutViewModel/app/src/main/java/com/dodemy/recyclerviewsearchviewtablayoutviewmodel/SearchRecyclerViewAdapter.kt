package com.dodemy.recyclerviewsearchviewtablayoutviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SearchRecyclerViewAdapter(var elements: ArrayList<DesiredObject>):
    RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return elements.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.configure(elements[position])
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun configure(obj: DesiredObject?) {
            view.textView.text = obj.text
        }
    }

}