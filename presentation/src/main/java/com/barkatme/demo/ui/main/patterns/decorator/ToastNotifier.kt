package com.barkatme.demo.ui.main.patterns.decorator

import android.content.Context
import android.widget.Toast

class ToastNotifier(
    private val notifier: Notifier,
    private val context: Context
) : Notifier {
    override fun notify(message: String) {
        notifier.notify(message)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}