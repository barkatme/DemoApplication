package com.barkatme.data.model.giphy


import kotlinx.serialization.Serializable

@Serializable
data class Images(
        val original: Original,
        val preview: Preview
)