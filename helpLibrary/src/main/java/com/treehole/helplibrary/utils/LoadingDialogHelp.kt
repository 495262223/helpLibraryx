package com.treehole.helplibrary.utils

import androidx.appcompat.app.AppCompatDialogFragment
import com.treehole.helplibrary.ui.dialog.loading.LoadingDialogFragment

/**
 * 自定义加载 loading Dialog
 */
object LoadingDialogHelp {

    // 默认的 LoadingDialogFragment
    private val defLoadingDialog = LoadingDialogFragment()

    private var _loadingDialog: AppCompatDialogFragment = defLoadingDialog
    val loadingDialog: AppCompatDialogFragment
        get() = _loadingDialog

    /**
     * 设置自定义 LoadDialogFragment
     * @param loadingDialog AppCompatDialogFragment
     */
    fun initLoadingDialog(loadingDialog: AppCompatDialogFragment) {
        _loadingDialog = loadingDialog
    }

    /**
     * 设置默认的 LoadDialogFragment
     */
    fun setDefaultLoadingDialogFragment() {
        _loadingDialog = defLoadingDialog
    }

}