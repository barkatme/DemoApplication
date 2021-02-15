package com.barkatme.demo.notifier

import android.content.Context
import android.widget.Toast
import com.barkatme.demo.domain.usecase.demo.notifier.Notifier

class ToastNotifier(private val notifier: Notifier, private val context: Context) : Notifier {
    override fun notify(message: String) {
        notifier.notify(message)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}