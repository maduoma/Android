package com.dodemy.recyclerviewsearchviewtablayoutviewmodel

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.search_activity_layout.*


class SearchActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView

    private val vm: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity_layout)
        recyclerView.adapter = vm.adapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        configureTabs()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_activity_menu, menu)
        searchView = menu?.findItem(R.id.searchView)?.actionView as SearchView
        configureSearch(searchView)
        return true
    }

    private fun configureSearch(searchView: SearchView)  {
        searchView.setOnSearchClickListener { toggleTabs(true) }
        searchView.setOnCloseListener {
            toggleTabs(false)
            vm.resetList()
            return@setOnCloseListener false
        }
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { return true }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.searchText = newText ?: ""
                return true
            }
        })
    }

    private fun toggleTabs(show: Boolean) {
        tabs.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun configureTabs() {
        addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    vm.searchOrder = if (p0.position == 0) SortOrder.ASCENDING else SortOrder.DESCENDING
                }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) { }
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    vm.searchOrder = if (p0.position == 0) SortOrder.ASCENDING else SortOrder.DESCENDING
                }
            }
        })
    }
}