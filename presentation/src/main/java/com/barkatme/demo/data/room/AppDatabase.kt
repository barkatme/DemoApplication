// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barkatme.demo.data.room.giphy.GifDao
import com.barkatme.demo.data.room.giphy.LocalGif

@Database(
    entities = [LocalGif::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
}