// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.model.giphy

data class Gif(
    val id: String,
    val title: String? = null,
    val url: String? = null,
    val previewUrl: String? = null
)