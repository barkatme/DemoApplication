package com.barkatme.data.repository

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo
import kotlinx.coroutines.Deferred

interface Repository {
    suspend fun test(): Todo

    suspend fun getPosts(): List<Post>

    suspend fun getComments(): List<Comment>

    fun getCommentsAsync(): Deferred<List<Comment>>
}