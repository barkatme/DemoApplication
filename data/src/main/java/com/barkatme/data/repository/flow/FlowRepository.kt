package com.barkatme.data.repository.flow

import com.barkatme.data.model.placeholder.Comment
import com.barkatme.data.model.placeholder.Post
import com.barkatme.data.model.placeholder.Todo
import kotlinx.coroutines.flow.Flow

interface FlowRepository {

    suspend fun todo(n: Int): Todo

    suspend fun getPosts(): Flow<Post>

    suspend fun getComments(): Flow<Comment>
}