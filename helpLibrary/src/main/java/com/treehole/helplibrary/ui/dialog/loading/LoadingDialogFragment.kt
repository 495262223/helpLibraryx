package com.treehole.helplibrary.ui.dialog.loading

import android.view.WindowManager
import com.treehole.helplibrary.R
import com.treehole.helplibrary.core.base.fragment.BaseDialogFragment
import com.treehole.helplibrary.core.extend.createViewModel
import com.treehole.helplibrary.databinding.FragmentLoadingDialogBinding

class LoadingDialogFragment :
    BaseDialogFragment<LoadingDialogViewModel, FragmentLoadingDialogBinding>() {

    override fun initViewModel(): LoadingDialogViewModel {
        return createViewModel(this)
    }

    override fun fragmentLayoutResId(): Int {
        return R.layout.fragment_loading_dialog
    }

    override fun fragmentFunc() {

    }

    override fun fragmentListener() {

    }

    override fun fragmentObserver() {

    }

    override fun onStart() {
        super.onStart()
        dialog?.run {
            setCanceledOnTouchOutside(false)
            setCancelable(false)
            window?.run {
                setBackgroundDrawableResource(android.R.color.transparent)
                val lp = attributes
                lp.dimAmount = 0f
                lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                attributes = lp
            }
        }
    }


}