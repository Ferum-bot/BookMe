package com.levit.book_me.core.utill

enum class PhoneRegionCodes(val designation: String, val code: String) {
    RUSSIA("RU", "+7"),
    GERMAN("DE", "+49");

    override fun toString(): String {
        return "$designation $code"
    }

    companion object {

        fun getAll(): List<PhoneRegionCodes>
            = listOf(
                RUSSIA,
                GERMAN,
            )

        fun getAllStrings(): List<String>
            = getAll().map { it.toString() }


        fun getAllCodes(): List<String>
            = getAll().map { it.code }

        fun getFromCode(code: String): PhoneRegionCodes {
            val allCodes = PhoneRegionCodes.getAll()
            allCodes.forEach {
                if (it.code == code) {
                    return it
                }
            }
            return RUSSIA
        }
    }
}