package com.treehole.helplibrary.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.treehole.helplibrary.core.base.BaseViewModel

/**
 * 带有 ViewModel 和 DataBinding 的 Fragment
 * @param BVM   ViewModel 的实例
 * @param DB    DataBinding 的实例
 * 如使用该 Fragment , 必须导入
 *
 *  buildFeatures {
 *    dataBinding true
 *  }
 */
abstract class BaseVMDBFragment<BVM:BaseViewModel, DB : ViewDataBinding> : BaseVMFragment<BVM>() {

    protected lateinit var dataBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, fragmentLayoutResId(), container, false)
        setContentView(dataBinding.root)
        setDataBinding(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}