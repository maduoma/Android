package com.dodemy.gadsproject_aad.model

class TopLearner {
    var name: String? = null
    var country: String? = null
    var hours = 0
    var badgeUrl: String? = null

    constructor() {}
    constructor(name: String?, country: String?, hours: Int, badgeUrl: String?) {
        this.name = name
        this.country = country
        this.hours = hours
        this.badgeUrl = badgeUrl
    }
}