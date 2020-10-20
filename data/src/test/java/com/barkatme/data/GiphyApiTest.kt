// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data


import com.barkatme.demo.domain.data.api.GiphyApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class GiphyApiTest : BaseTest {

    private val giphyApi: GiphyApi by inject()

    @Test
    fun testTrending() {
        runBlocking {
            val response = giphyApi.trending()
            response.forEach { println(it.title) }
            assert(response.isNotEmpty()) { "response on giphy trending is empty" }
        }
    }

    @Test
    fun testSearch() {
        runBlocking {
            val response = giphyApi.search("search")
            response.forEach { println(it.title) }
            assert(response.isNotEmpty()) { "response on giphy search is empty" }
        }
    }

}