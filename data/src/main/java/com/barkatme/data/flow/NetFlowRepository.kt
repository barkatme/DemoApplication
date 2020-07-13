package com.barkatme.data.flow

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.list

class NetFlowRepository : FlowRepository {

    override suspend fun todo(n: Int) = withContext(Dispatchers.IO) {
            "https://jsonplaceholder.typicode.com/todos/$n".httpGet()
                .await(kotlinxDeserializerOf(Todo.serializer))
    }

    override suspend fun getPosts() = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/posts".httpGet()
            .await(kotlinxDeserializerOf(Post.serializer.list))
            .asFlow()
    }

    override suspend fun getComments() =
        "https://jsonplaceholder.typicode.com/comments".httpGet()
            .await(kotlinxDeserializerOf(Comment.serializer.list))
            .asFlow()
}