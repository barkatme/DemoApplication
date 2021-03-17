package com.barkatme.demo.ui.main.patterns.decorator


class EmptyNotifier : Notifier {
    override fun notify(message: String){}
}