package com.barkatme.data.api.placeholder

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo
import com.barkatme.data.model.placeholder.toDomainModel
import com.barkatme.demo.domain.data.api.PlaceholderApi
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.list

class PlaceholderApiImpl : PlaceholderApi {
    override suspend fun test() = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/todos/1".httpGet()
            .await(kotlinxDeserializerOf(Todo.serializer))
            .toDomainModel()
    }

    override suspend fun getPosts() = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/posts".httpGet()
            .await(kotlinxDeserializerOf(Post.serializer.list))
            .map { it.toDomainModel() }
    }

    override suspend fun getComments() =
        "https://jsonplaceholder.typicode.com/comments".httpGet()
            .await(kotlinxDeserializerOf(Comment.serializer.list))
            .map { it.toDomainModel() }

    override fun getCommentsAsync() =
        CoroutineScope(Dispatchers.IO).async {
            "https://jsonplaceholder.typicode.com/comments".httpGet()
                .await(kotlinxDeserializerOf(Comment.serializer.list))
                .map { it.toDomainModel() }
        }
}