package com.treehole.helplibrary.core.extend

import com.blankj.utilcode.util.ColorUtils

fun Int.colorId(): Int {
    return ColorUtils.getColor(this)
}