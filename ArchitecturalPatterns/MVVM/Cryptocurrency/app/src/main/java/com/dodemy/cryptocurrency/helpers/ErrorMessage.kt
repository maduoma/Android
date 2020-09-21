package com.dodemy.cryptocurrency.helpers


class ErrorMessage {

    private val message: String

    fun getMessage(): String {
        return message
    }

    constructor(text: String) {
        this.message = text
    }
}