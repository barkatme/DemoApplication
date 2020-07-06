package com.barkatme.data

import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
data class Todo(val userId: Int, val id: Int, val title: String, val completed: Boolean)

val todoSerializer = Todo.serializer()

class NetRepository {
    suspend fun test() = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/todos/1".httpGet()
            .await(kotlinxDeserializerOf(todoSerializer))
    }
}