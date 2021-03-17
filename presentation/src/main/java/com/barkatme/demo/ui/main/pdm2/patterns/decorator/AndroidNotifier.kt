package com.barkatme.demo.ui.main.pdm2.patterns.decorator

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.barkatme.demo.R
import com.barkatme.demo.ui.main.MainActivity

class AndroidNotifier(private val notifier: Notifier, private val context: Context) : Notifier {

    companion object {
        const val CHANNEL_ID = "barkatmedemo"
        const val NOTIFICATION_ID = 15
    }

    private val notificationManager: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    override fun notify(message: String) {
        notifier.notify(message)
        showAndroidNotification(message)
    }

    private fun showAndroidNotification(message: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(null)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        context.createNotificationChannel()
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun Context.createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_name)
            val descriptionText = getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}