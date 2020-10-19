package com.barkatme.data.model.placeholder

import kotlinx.serialization.Serializable

@Serializable
data class Todo(val userId: Int, val id: Int, val title: String, val completed: Boolean) {
    companion object {
        @JvmStatic
        val serializer = serializer()
    }
}

fun Todo.toDomainModel() =
    com.barkatme.demo.domain.model.placeholder.Todo(userId, id, title, completed)
