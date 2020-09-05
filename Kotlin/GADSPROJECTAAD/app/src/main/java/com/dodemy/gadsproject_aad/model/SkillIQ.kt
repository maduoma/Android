package com.dodemy.gadsproject_aad.model

class SkillIQ {
    var name: String? = null
    var country: String? = null
    var score = 0
    var badgeUrl: String? = null

    constructor() {}
    constructor(name: String?, country: String?, score: Int, badgeUrl: String?) {
        this.name = name
        this.country = country
        this.score = score
        this.badgeUrl = badgeUrl
    }
}