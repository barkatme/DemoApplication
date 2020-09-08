// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.extensions

import android.view.View

fun View.doOnClick(action: () -> Unit) {
    this.setOnClickListener { action.invoke() }
}