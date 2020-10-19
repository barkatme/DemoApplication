package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.placeholder.Comment
import com.barkatme.demo.domain.model.placeholder.Post
import com.barkatme.demo.domain.model.placeholder.Todo
import kotlinx.coroutines.Deferred

interface JPRepository {
    suspend fun test(): Todo

    suspend fun getPosts(): List<Post>

    suspend fun getComments(): List<Comment>

    fun getCommentsAsync(): Deferred<List<Comment>>
}