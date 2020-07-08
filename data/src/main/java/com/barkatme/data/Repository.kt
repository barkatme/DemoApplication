package com.barkatme.data

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo

interface Repository {
    suspend fun test(): Todo

    suspend fun getPosts(): List<Post>

    suspend fun getComments(): List<Comment>
}