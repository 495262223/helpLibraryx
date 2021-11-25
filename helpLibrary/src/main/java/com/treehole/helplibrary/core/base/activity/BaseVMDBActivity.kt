package com.treehole.helplibrary.core.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.treehole.helplibrary.core.base.BaseViewModel

/**
 * 带有 ViewModel 和 DataBinding 的Activity
 * @param BVM   ViewModel 的实例
 * @param DB    DataBinding 的实例
 * 如使用该 Activity , 必须导入
 *
 *  buildFeatures {
 *    dataBinding true
 *  }
 */
abstract class BaseVMDBActivity<BVM : BaseViewModel, DB : ViewDataBinding> : BaseVMActivity<BVM>() {

    protected lateinit var dataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        setDataBinding(true)
        super.onCreate(savedInstanceState)
    }

    /**
     * 重写基类的 bindDataBinding 函数,绑定 DataBinding
     */
    override fun bindDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, activityLayoutResId())
    }
}