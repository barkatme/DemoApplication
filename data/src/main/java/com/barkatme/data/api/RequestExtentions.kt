package com.barkatme.data.api

import com.github.kittinunf.fuel.core.Request
import com.google.firebase.crashlytics.FirebaseCrashlytics

val crashlytics = FirebaseCrashlytics.getInstance()

fun Request.log(): Request {
    crashlytics.log(response().first.toString())
    response().second.toString().split("Body : ").let {
        crashlytics.log(it[0])
        crashlytics.log(it[1])
    }
    return this
}