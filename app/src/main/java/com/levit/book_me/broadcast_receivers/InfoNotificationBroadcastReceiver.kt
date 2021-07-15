package com.levit.book_me.broadcast_receivers

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.levit.book_me.R
import com.levit.book_me.core.models.NotificationModel
import com.levit.book_me.core.utill.NotificationsUtil
import com.levit.book_me.ui.activities.main_screen.MainScreenActivity

class InfoNotificationBroadcastReceiver: BroadcastReceiver() {

    companion object {

        private const val CHANNEL_ID = NotificationsUtil.INFO_CHANNEL_ID

        private const val MODEL_NAME = NotificationsUtil.NOTIFICATION_MODEL_NAME

        private const val REQUEST_CODE = NotificationsUtil.PENDING_INTENT_INFO_REQUEST_CODE

        private const val FLAGS = NotificationsUtil.PENDING_INTENT_INFO_FLAGS
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        intent ?: return

        val model = intent.getParcelableExtra<NotificationModel>(MODEL_NAME)
            ?: return

        val builder = getNotificationBuilder(model, context)
        showNotification(model.id, builder.build(), context)
    }

    private fun getNotificationBuilder(
        model: NotificationModel, context: Context
    ): NotificationCompat.Builder {
        val intent = getInfoIntent(context)
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_book_me_icon)
            .setContentTitle(model.title)
            .setContentText(model.text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(intent)
            .setCategory(NotificationCompat.CATEGORY_EVENT)
            .setAutoCancel(true)
    }

    private fun getInfoIntent(context: Context): PendingIntent {
        val intent = Intent(context.applicationContext, MainScreenActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(
            context.applicationContext, REQUEST_CODE, intent, FLAGS
        )
    }

    private fun showNotification(
        id: Int, notification: Notification, context: Context
    ) = with(NotificationManagerCompat.from(context)) {
        notify(id, notification)
    }
}