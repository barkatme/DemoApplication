// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.data.room.giphy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barkatme.data.model.giphy.GifData

@Entity(tableName = Gif.TABLE_NAME)
data class Gif(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String? = null
) {
    companion object {
        const val TABLE_NAME = "gifs"
        const val COLUMN_ID = "gif_id"
        const val COLUMN_TITLE = "gif_title"
    }
}

fun GifData.toPresentationModel() = Gif(
    id, title
)