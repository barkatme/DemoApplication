// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barkatme.data.repository.room.giphy.GifDao
import com.barkatme.data.repository.room.giphy.LocalGif

@Database(
    entities = [LocalGif::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
}