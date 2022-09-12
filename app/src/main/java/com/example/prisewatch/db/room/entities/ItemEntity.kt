package com.example.prisewatch.db.room.entities

import androidx.room.*
import com.example.myapplication.model.Item

@Entity(
    tableName = "items",
    indices = [
        Index("url_link", unique = true)
    ]
)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "url_link") var urlLink: String,
    @ColumnInfo(name = "url_img") var urlImg: String,
    @ColumnInfo(name = "host") var host: String
)

