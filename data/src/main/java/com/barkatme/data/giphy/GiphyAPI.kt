package com.barkatme.data.giphy

object GiphyAPI {

    class Ratings {
        companion object {
            const val G = "G"
            const val PG = "PG"
            const val PG13 = "PG-13"
            const val R = "R"
        }
    }

    const val URL = "https://api.giphy.com/v1/gifs/"
    const val KEY = "3gSLYjFzyfEBSiZ60ceDOjiwjU4WVluT"
    const val defaultRating =
        Ratings.G
    const val DEFAULT_OFFSET = 0
    const val DEFAULT_LIMIT = 10
    const val DEFAULT_LANGUAGE = "en"
}