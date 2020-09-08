// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.extensions

import android.widget.SearchView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })
    return query
}

@ExperimentalCoroutinesApi
fun androidx.appcompat.widget.SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })
    return query
}