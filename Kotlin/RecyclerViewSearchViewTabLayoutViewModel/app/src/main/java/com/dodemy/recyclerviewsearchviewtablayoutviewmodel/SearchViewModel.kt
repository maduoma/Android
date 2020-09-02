package com.dodemy.recyclerviewsearchviewtablayoutviewmodel

import androidx.lifecycle.ViewModel
import javax.sql.DataSource


class SearchViewModel(): ViewModel(), KoinComponent {

    private val datasource: DataSource by inject()

    val adapter: SearchRecyclerViewAdapter by inject {
        parametersOf(datasource.elements)
    }

    var searchOrder = SortOrder.ASCENDING
        set(value) {
            field = value
            filterData()
        }

    var searchText: String = ""
        set(value) {
            field = value
            filterData()
        }

    private fun filterData() {
        if (searchText.isEmpty()) {
            resetList()
            return
        }
        var filteredElements = datasource.elements.filter { it.text.contains(searchText) }
        when(searchOrder) {
            SortOrder.ASCENDING -> filteredElements.sortBy { it.text }
            SortOrder.DESCENDING -> filteredElements.sortByDescending { it.text }
        }
        adapter.elements = datasource.elements.filter { it.text.contains(searchText) }
        adapter.notifyDataSetChanged()
    }

    fun resetList() {
        adapter.elements = datasource.elements
        adapter.notifyDataSetChanged()
    }
}