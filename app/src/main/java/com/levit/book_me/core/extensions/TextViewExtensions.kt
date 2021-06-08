package com.levit.book_me.core.extensions

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.addClickableText(text: String, inNewLine: Boolean = true, action: (widget: View) -> Unit) {
    configureTextView(this)

    val spannableText = if(inNewLine) {
        val resultText = getText().toString() + "\n" + text
        SpannableString(resultText)
    } else {
        val resultText = getText().toString() + text
        SpannableString(resultText)
    }
    val clickableSpan = object: ClickableSpan() {

        override fun onClick(widget: View) {
            action.invoke(widget)
        }
    }

    val startIndex = getText().length + 1
    val endIndex = spannableText.length
    spannableText.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    setText(spannableText)
}

fun TextView.addClickableText(@StringRes id: Int, inNewLine: Boolean = true, action: (widget: View) -> Unit) {
    val text = context.getString(id)
    addClickableText(text, inNewLine, action)
}

private fun configureTextView(view: TextView) {
    view.movementMethod = LinkMovementMethod.getInstance()
    view.highlightColor = Color.TRANSPARENT
}