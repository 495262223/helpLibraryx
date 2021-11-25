package com.treehole.helplibrary.core.base.activity

import com.treehole.helplibrary.core.base.BaseViewModel
import com.treehole.helplibrary.utils.LoadingDialogHelp

/**
 * 带有 ViewModel 的 Activity
 */
abstract class BaseVMActivity<BVM : BaseViewModel> : BaseActivity() {

    protected val viewModel by lazy { initViewModel() }

    abstract fun initViewModel(): BVM

    override fun viewModelObserver() {
        viewModel.loadingDialogState.observe(this) {
            if (it) {
                if (!LoadingDialogHelp.loadingDialog.isVisible)
                    LoadingDialogHelp.loadingDialog.show(supportFragmentManager, "loading")
            } else {
                if (LoadingDialogHelp.loadingDialog.isVisible)
                    LoadingDialogHelp.loadingDialog.dismiss()
            }
        }
    }

}