package com.treehole.helplibrary.core.extend

import androidx.databinding.ViewDataBinding

/**
 * 用来给 DataBinding 布局中的字段赋值
 * @receiver ViewDataBinding
 * @param pairs Array<out Pair<Int, Any>>
 */
fun ViewDataBinding.variables(vararg pairs: Pair<Int, Any>) {
    pairs.forEach {
        this.setVariable(it.first, it.second)
    }
}

/**
 * 用来给 DataBinding 布局中的字段赋值
 * @receiver ViewDataBinding
 * @param block [@kotlin.ExtensionFunctionType] Function1<HashMap<Int, Any>, Unit>
 */
inline fun ViewDataBinding.variables(block: HashMap<Int, Any>.() -> Unit) {
    val map = HashMap<Int, Any>()
    map.block()
    map.forEach {
        this.setVariable(it.key, it.value)
    }
}