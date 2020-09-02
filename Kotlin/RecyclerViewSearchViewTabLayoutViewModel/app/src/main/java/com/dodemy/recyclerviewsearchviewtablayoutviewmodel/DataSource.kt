package com.dodemy.recyclerviewsearchviewtablayoutviewmodel

data class DesiredObject(var text: String)

class DataSource() {

    lateinit var elements: MutableArrayList<DesiredObject>

}