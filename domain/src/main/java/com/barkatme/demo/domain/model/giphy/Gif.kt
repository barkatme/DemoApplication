// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.model.giphy

data class Gif(
    val id: String,
    val title: String = "",
    val url: String = "",
    val previewUrl: String = ""
)