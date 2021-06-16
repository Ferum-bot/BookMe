package com.levit.book_me.core.enums

/**
 * This enum class shows current app status.
 * Uses to handle launch event.
 */
enum class CurrentUserStatus {
    NOT_AUTHORIZED,
    AUTHORIZED_BUT_PROFILE_NOT_CREATED,
    AUTHORIZED_PROFILE_CREATED;

    companion object {

        fun getFromName(name: String): CurrentUserStatus {
            return when(name) {
                "AUTHORIZED_BUT_PROFILE_NOT_CREATED" -> AUTHORIZED_BUT_PROFILE_NOT_CREATED
                "AUTHORIZED_PROFILE_CREATED" -> AUTHORIZED_PROFILE_CREATED
                else -> NOT_AUTHORIZED
            }
        }
    }
}