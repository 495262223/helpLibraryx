package com.treehole.helplibrary.core.binding

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

object TextViewAdapter {

    @JvmStatic
    @BindingAdapter("textColor")
    fun setTextColor(textView: TextView, @ColorRes textColorRes: Int) {
        if (textColorRes != 0) textView.setTextColor(
            ContextCompat.getColor(
                textView.context,
                textColorRes
            )
        )
    }

    @JvmStatic
    @BindingAdapter("text")
    fun setText(textView: TextView, @StringRes textRes: Int) {
        if (textRes != 0) textView.setText(textRes)
    }

}