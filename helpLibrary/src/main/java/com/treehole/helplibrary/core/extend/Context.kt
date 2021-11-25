package com.treehole.helplibrary.core.extend

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.goto() {
    this.startActivity(intent<T>(this) {})
}

inline fun <reified T> Context.goto(block: Intent.() -> Unit) {
    this.startActivity(intent<T>(this, block))
}

inline fun <reified T> intent(mContext: Context, block: Intent.() -> Unit): Intent {
    val intent = Intent(mContext, T::class.java)
    intent.block()
    return intent
}