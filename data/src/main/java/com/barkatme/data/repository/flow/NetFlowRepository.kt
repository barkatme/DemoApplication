package com.barkatme.data.repository.flow

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer

class NetFlowRepository : FlowRepository {

    override suspend fun todo(n: Int) = withContext(Dispatchers.IO) {
            "https://jsonplaceholder.typicode.com/todos/$n".httpGet()
                .timeout(5000)
                .await(kotlinxDeserializerOf(Todo.serializer))
    }

    override suspend fun getPosts() = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/posts".httpGet()
            .await(kotlinxDeserializerOf(ListSerializer(Post.serializer)))
            .asFlow()
    }

    override suspend fun getComments() =
        "https://jsonplaceholder.typicode.com/comments".httpGet()
            .await(kotlinxDeserializerOf(ListSerializer(Comment.serializer)))
            .asFlow()
}