package com.treehole.helplibrary.core.binding

import android.view.View
import androidx.databinding.BindingAdapter

object ViewAdapter {

    @JvmStatic
    @BindingAdapter("onClick")
    fun setClick(view: View, onClickListener: View.OnClickListener?) {
        if (onClickListener != null) view.setOnClickListener(onClickListener)
    }

}