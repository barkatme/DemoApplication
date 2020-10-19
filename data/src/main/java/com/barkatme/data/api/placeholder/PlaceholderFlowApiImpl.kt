package com.barkatme.data.api.placeholder

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo
import com.barkatme.data.model.placeholder.toDomainModel
import com.barkatme.demo.domain.data.api.PlaceholderFlowApi
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer

class PlaceholderFlowApiImpl : PlaceholderFlowApi {

    override suspend fun todo(n: Int) = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/todos/$n".httpGet()
            .timeout(5000)
            .await(kotlinxDeserializerOf(Todo.serializer))
            .toDomainModel()
    }

    override suspend fun getPosts() = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/posts".httpGet()
            .await(kotlinxDeserializerOf(ListSerializer(Post.serializer)))
            .asFlow()
            .map { it.toDomainModel() }
    }

    override suspend fun getComments() =
        "https://jsonplaceholder.typicode.com/comments".httpGet()
            .await(kotlinxDeserializerOf(ListSerializer(Comment.serializer)))
            .asFlow()
            .map { it.toDomainModel() }
}