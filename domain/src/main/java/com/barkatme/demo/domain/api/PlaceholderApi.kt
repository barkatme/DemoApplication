package com.barkatme.demo.domain.api

import com.barkatme.demo.domain.model.placeholder.Comment
import com.barkatme.demo.domain.model.placeholder.Post
import com.barkatme.demo.domain.model.placeholder.Todo
import kotlinx.coroutines.Deferred

interface PlaceholderApi {
    suspend fun test(): Todo

    suspend fun getPosts(): List<Post>

    suspend fun getComments(): List<Comment>

    fun getCommentsAsync(): Deferred<List<Comment>>
}