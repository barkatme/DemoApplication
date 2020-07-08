package com.barkatme.data

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.*
import kotlinx.serialization.builtins.list

class NetRepository : Repository {
    override suspend fun test() = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/todos/1".httpGet()
            .await(kotlinxDeserializerOf(Todo.serializer))
    }

    override suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        "https://jsonplaceholder.typicode.com/posts".httpGet()
            .await(kotlinxDeserializerOf(Post.serializer.list))
    }

    override suspend fun getComments(): List<Comment> =
        "https://jsonplaceholder.typicode.com/comments".httpGet()
            .await(kotlinxDeserializerOf(Comment.serializer.list))

    override fun getCommentsAsync(): Deferred<List<Comment>> =
        CoroutineScope(Dispatchers.IO).async {
            "https://jsonplaceholder.typicode.com/comments".httpGet()
                .await(kotlinxDeserializerOf(Comment.serializer.list))
        }

}