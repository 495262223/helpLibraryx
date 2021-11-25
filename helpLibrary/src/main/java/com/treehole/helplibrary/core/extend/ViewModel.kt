package com.treehole.helplibrary.core.extend

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.treehole.helplibrary.core.base.BaseViewModel

/**
 * 创建 ViewModel 函数
 * @return VM
 */
inline fun <reified VM : BaseViewModel> createViewModel(owner: ViewModelStoreOwner): VM {
    return ViewModelProvider(owner)[VM::class.java]
}

/**
 * 创建 ViewModel 工厂函数
 * @param factory Factory
 * @return VM
 */
inline fun <reified VM : BaseViewModel> createViewModel(
    owner: ViewModelStoreOwner,
    factory: ViewModelProvider.Factory
): VM {
    return ViewModelProvider(owner, factory)[VM::class.java]
}