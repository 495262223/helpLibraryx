package com.treehole.helplibrary.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 所有 ViewModel 的基类
 */
open class BaseViewModel : ViewModel() {

    var isLoadFunc = false // 是否加载过当前 Fragment --- false 未加载 or true 已加载
    // 用来控制 LoadingDialog 的 show() and dismiss()
    private val _loadingDialogState = MutableLiveData<Boolean>()
    val loadingDialogState: LiveData<Boolean>
        get() = _loadingDialogState

    fun setLoadingDialogState(state: Boolean) {
        _loadingDialogState.value = state
    }

}