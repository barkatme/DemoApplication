package com.barkatme.data.model.placeholder

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
) {
    companion object {
        @JvmStatic
        val serializer = serializer()
    }
}

fun Comment.toDomainModel() =
    com.barkatme.demo.domain.model.placeholder.Comment(body, email, id, name, postId)