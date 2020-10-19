package com.barkatme.demo.domain.repository


import com.barkatme.demo.domain.model.placeholder.Comment
import com.barkatme.demo.domain.model.placeholder.Post
import com.barkatme.demo.domain.model.placeholder.Todo
import kotlinx.coroutines.flow.Flow

interface FlowRepository {

    suspend fun todo(n: Int): Todo

    suspend fun getPosts(): Flow<Post>

    suspend fun getComments(): Flow<Comment>
}