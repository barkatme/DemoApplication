// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.firstpdm.room

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface GifDao {
    @Query("SELECT * FROM gifs")
    suspend fun getGifs(): List<LocalGif>
//
//    @Query("SELECT * FROM gifs where gif_title LIKE '%:queue%'")
//    suspend fun searchGifs(queue: String): List<LocalGif>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGif(localGif: LocalGif)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateGif(localGif: LocalGif)

    @Delete
    fun deleteGif(localGif: LocalGif)

    @Query("SELECT * FROM gifs order by gif_id")
    fun loadAllGifs(): DataSource.Factory<Int, LocalGif>

    @Query("SELECT * FROM gifs where gif_title LIKE :name order by gif_id")
    fun loadAllGifsByTitle(name: String): DataSource.Factory<Int, LocalGif>
}