package com.levit.book_me.core_base.utils

import java.util.*

class Optional<T>(
    private val value: T? = null
) {
    companion object {
        private val EMPTY: Optional<*> = Optional<Any>()

        @Suppress("UNCHECKED_CAST")
        fun <T> empty(): Optional<T> {
            return EMPTY as Optional<T>
        }

        fun <T> of(value: T?): Optional<T> {
            return if (value == null) empty() else Optional(value)
        }
    }

    fun getOrNull(): T? {
        return value
    }

    fun getOrDefault(defValue: T): T {
        return value ?: defValue
    }

    fun get(): T {
        if (value == null) {
            throw NoSuchElementException("No value present")
        }
        return value
    }

    fun isPresent(): Boolean {
        return value != null
    }

    fun isNotPresent(): Boolean {
        return !isPresent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        } else if (other !is Optional<*>) {
            return false
        }
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return if (value != null) String.format("Optional[%s]", value) else "Optional.empty"
    }
}
