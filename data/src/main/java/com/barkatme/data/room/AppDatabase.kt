// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barkatme.data.room.giphy.GifDao
import com.barkatme.data.room.giphy.LocalGif

@Database(
    entities = [LocalGif::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
}