package com.treehole.helplibrary.core.extend

inline fun <T> handleResult(
    data: Result<T>,
    success: (value: T) -> Unit,
    error: (e: Exception) -> Unit
) {
    try {
        val v = data.getOrNull()
        if (v != null) {
            success(v)
        } else {
            error(Exception("data is null"))
        }
    } catch (e: Exception) {
        error(e)
    }
}