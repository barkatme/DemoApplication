// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data


import com.barkatme.demo.domain.api.GiphyApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class GiphyApiTest : BaseTest {

    private val api: GiphyApi by inject()

    @Test
    fun testTrending() {
        runBlocking {
            val response = api.trending()
            assert(response.isNotEmpty()) { "response on giphy trending is empty" }
        }
    }

    @Test
    fun testSearch() {
        runBlocking {
            val response = api.search("search")
            assert(response.isNotEmpty()) { "response on giphy search is empty" }
        }
    }

}