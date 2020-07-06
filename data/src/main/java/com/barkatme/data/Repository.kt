package com.barkatme.data

interface Repository {
    suspend fun test(): Todo
}