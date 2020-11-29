package com.dodemy.room_notekeeper

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note (
    @field:PrimaryKey
    val id: String,
    val note: String
)