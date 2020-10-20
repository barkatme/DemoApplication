// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data

import com.barkatme.demo.domain.data.api.PlaceholderApi
import com.barkatme.demo.domain.data.api.PlaceholderFlowApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class PlaceholderApiTest : BaseTest {

    private val api: PlaceholderApi by inject()
    private val flowApi: PlaceholderFlowApi by inject()

    @Test
    fun testTodo() {
        runBlocking {
            val response = async { api.test() }
            val responseFlow = async { flowApi.todo(1) }

            assert(
                response.await() == responseFlow.await()
            ) { "1st todo from placeholder didn't matched for different repositories impl" }
        }
    }

    @Test
    fun testPosts() {
        runBlocking {
            val response = async { api.getPosts() }
            val responseFlow = async { flowApi.getPosts() }
            assert(
                response.await() == responseFlow.await().toList()
            ) { "get posts responses doesn't match for different repositories impl" }
        }
    }


}