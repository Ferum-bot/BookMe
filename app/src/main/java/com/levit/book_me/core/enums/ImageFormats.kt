package com.levit.book_me.core.enums

enum class ImageFormats(val string: String) {
    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg");

    override fun toString(): String
        = string
}