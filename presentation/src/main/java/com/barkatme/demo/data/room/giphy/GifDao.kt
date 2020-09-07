// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.data.room.giphy

import androidx.room.*

@Dao
interface GifDao {
    @Query("SELECT * FROM gifs")
    suspend fun getGifs(): List<Gif>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGif(gif: Gif)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateGif(gif: Gif)

    @Delete
    fun deleteGif(gif: Gif)
}