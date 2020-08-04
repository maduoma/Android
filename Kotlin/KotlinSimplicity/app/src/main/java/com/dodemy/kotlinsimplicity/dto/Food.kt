package com.dodemy.kotlinsimplicity.dto

data class Food (var name: String = "", var calories: Int = 0, var cost: String = "0.00", var prepTime: String = "", var type: String = "Unclassified") {
    override fun toString() = "$name $type"
}