package com.barkatme.data.api.placeholder

import com.barkatme.data.model.placeholder.toDomainModel
import com.barkatme.demo.domain.api.PlaceholderApi
import com.barkatme.demo.domain.model.placeholder.Comment
import com.barkatme.demo.domain.model.placeholder.Post
import com.barkatme.demo.domain.model.placeholder.Todo
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.*
import kotlinx.serialization.builtins.ListSerializer

class PlaceholderApiImpl : PlaceholderApi {
    override suspend fun test(): Todo = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/todos/1".httpGet()
            .await(kotlinxDeserializerOf(com.barkatme.data.model.placeholder.Todo.serializer))
            .toDomainModel()
    }

    override suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/posts".httpGet()
            .await(kotlinxDeserializerOf(ListSerializer(com.barkatme.data.model.placeholder.Post.serializer)))
            .map { it.toDomainModel() }
    }

    override suspend fun getComments(): List<Comment> =
        "https://jsonplaceholder.typicode.com/comments".httpGet()
            .await(kotlinxDeserializerOf(ListSerializer(com.barkatme.data.model.placeholder.Comment.serializer)))
            .map { it.toDomainModel() }

    override fun getCommentsAsync(): Deferred<List<Comment>> =
        CoroutineScope(Dispatchers.IO).async {
            "https://jsonplaceholder.typicode.com/comments".httpGet()
                .await(kotlinxDeserializerOf(ListSerializer(com.barkatme.data.model.placeholder.Comment.serializer)))
                .map { it.toDomainModel() }
        }
}