package com.levit.book_me.services

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.levit.book_me.core.enums.FirebaseMessageType
import com.levit.book_me.core.models.NotificationModel
import com.levit.book_me.core.utill.NotificationsUtil
import java.util.*

class FirebaseService: FirebaseMessagingService() {

    companion object {

        private const val MODEL_NAME = NotificationsUtil.NOTIFICATION_MODEL_NAME

        private const val TYPE_NAME = NotificationsUtil.FIREBASE_NOTIFICATION_TYPE_NAME
        private const val TITLE_NAME = NotificationsUtil.FIREBASE_NOTIFICATION_TITLE_NAME
        private const val TEXT_NAME = NotificationsUtil.FIREBASE_NOTIFICATION_TEXT_NAME

        private const val INFO_MESSAGE = "info_message"

        private const val CHAT_MESSAGE = "chat_message"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        safeAndSendToServerToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val model = message.getNotificationModel()
        val type = message.getNotificationType()

        when(type) {
            FirebaseMessageType.INFO_MESSAGE -> sendInfoNotification(model)
            FirebaseMessageType.CHAT_MESSAGE -> sendMessageNotification(model)
            FirebaseMessageType.UN_DEFINED -> sendDefaultNotification(message)
        }
    }

    private fun sendInfoNotification(model: NotificationModel) {
        val intent = Intent().apply {
            action = ".broadcast_receivers.InfoNotificationBroadcastReceiver"
            putExtra(MODEL_NAME, model)
        }
        sendBroadcast(intent)
    }

    private fun sendMessageNotification(model: NotificationModel) {
        val intent = Intent().apply {
            action = ".broadcast_receivers.MessageNotificationBroadcastReceiver"
            putExtra(MODEL_NAME, model)
        }
        sendBroadcast(intent)
    }

    private fun sendDefaultNotification(message: RemoteMessage) {
        val notification = message.notification ?: return
        val text = notification.body ?: return
        val title = notification.title ?: return
        val model = NotificationModel(
            id = UUID.randomUUID().clockSequence(),
            title, text,
        )
        sendInfoNotification(model)
    }

    private fun safeAndSendToServerToken(token: String) {

    }

    private fun RemoteMessage.getNotificationModel(): NotificationModel {
        val text = data[TEXT_NAME].orEmpty()
        val title = data[TITLE_NAME].orEmpty()
        return NotificationModel(
            id = UUID.randomUUID().clockSequence(),
            title, text,
        )
    }

    private fun RemoteMessage.getNotificationType(): FirebaseMessageType {
        val rawType = data[TYPE_NAME]
        return when (rawType.orEmpty().toLowerCase(Locale.ROOT)) {
            INFO_MESSAGE -> FirebaseMessageType.INFO_MESSAGE
            CHAT_MESSAGE -> FirebaseMessageType.CHAT_MESSAGE
            else -> FirebaseMessageType.UN_DEFINED
        }
    }
}