package com.treehole.helplibrary.core.base.fragment

import com.treehole.helplibrary.core.base.BaseViewModel
import com.treehole.helplibrary.utils.LoadingDialogHelp

/**
 * 带有 ViewModel 的 Fragment
 */
abstract class BaseVMFragment<BVM : BaseViewModel> : BaseFragment() {

    protected val viewModel by lazy { initViewModel() }

    abstract fun initViewModel(): BVM

    override fun viewModelObserver() {
        viewModel.loadingDialogState.observe(this) {
            if (it) {
                if (!LoadingDialogHelp.loadingDialog.isVisible)
                    LoadingDialogHelp.loadingDialog.show(childFragmentManager, "loading")
            } else {
                if (LoadingDialogHelp.loadingDialog.isVisible)
                    LoadingDialogHelp.loadingDialog.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.isLoadFunc){
            fragmentLazyFunc()
            viewModel.isLoadFunc = true
        }
    }

}