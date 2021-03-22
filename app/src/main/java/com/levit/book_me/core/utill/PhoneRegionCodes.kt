package com.levit.book_me.core.utill

enum class PhoneRegionCodes(val designation: String, val code: String) {
    RUSSIA("RU", "+7"),
    GERMAN("DE", "+49");

    fun getString(): String {
        return "$designation $code"
    }

    companion object {

        fun getAll(): List<String> {
            return listOf(
                RUSSIA.getString(),
                GERMAN.getString(),
            )
        }

        fun getAllCodes(): List<String> {
            return listOf(
                RUSSIA.code,
                GERMAN.code,
            )
        }
    }
}