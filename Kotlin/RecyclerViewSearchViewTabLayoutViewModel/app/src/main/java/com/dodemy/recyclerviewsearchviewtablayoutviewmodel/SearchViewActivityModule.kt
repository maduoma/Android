package com.dodemy.recyclerviewsearchviewtablayoutviewmodel;

val searchViewActivityModule = module {

        viewModel { SearchViewModel() }

        factory { DataSource() }
        factory { (elements: ArrayList<DesiredObject>) -> SearchRecyclerViewAdapter(elements) }

        }