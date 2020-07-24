package com.barkatme.demo.model.giphy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gif(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val gifId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "original_url")
    val originalUrl: String,
    @ColumnInfo(name = "original_height")
    val originalHeight: Int,
    @ColumnInfo(name = "preview_url")
    val previewUrl: String
)