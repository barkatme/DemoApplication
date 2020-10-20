// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.room.giphy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barkatme.demo.domain.model.giphy.Gif

@Entity(tableName = LocalGif.TABLE_NAME)
data class LocalGif(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String? = null,
    @ColumnInfo(name = COLUMN_URL)
    val url: String? = null,
    @ColumnInfo(name = COLUMN_PREVIEW_URL)
    val previewUrl: String? = null
) {
    companion object {
        const val TABLE_NAME = "gifs"
        const val COLUMN_ID = "gif_id"
        const val COLUMN_TITLE = "gif_title"
        const val COLUMN_URL = "gif_url"
        const val COLUMN_PREVIEW_URL = "gif_preview_url"
    }
}

fun LocalGif.toGif() = Gif(id, title, url, previewUrl)
fun Gif.toLocalGif() = LocalGif(id, title, url, previewUrl)