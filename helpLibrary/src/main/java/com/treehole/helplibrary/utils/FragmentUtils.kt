package com.treehole.helplibrary.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.treehole.helplibrary.core.base.fragment.BaseFragment

/**
 * 关于 Fragment 的一些帮助
 */
object FragmentUtils {

    fun add(
        fragmentManager: FragmentManager,
        fragments: Array<BaseFragment>,
        containerId: Int
    ) {
        add(fragmentManager, fragments, containerId, -1)
    }

    /**
     * 添加 Fragment 到 Activity
     * @param fragmentManager FragmentManager
     * @param fragments Array<BaseFragment>
     * @param containerId Int
     * @param showIndex Int
     */
    fun add(
        fragmentManager: FragmentManager,
        fragments: Array<BaseFragment>,
        containerId: Int,
        showIndex: Int
    ) {
        if (fragments.isNotEmpty()) {
            fragmentManager.beginTransaction().apply {
                fragments.forEachIndexed { index, fragment ->
                    add(containerId, fragment)
                    if (index == showIndex) {
                        show(fragment)
                        setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                    } else {
                        hide(fragment)
                        setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                    }
                }
            }.commit()
        }
    }

    fun showHide(showIndex: Int, fragments: Array<BaseFragment>) {
        if (fragments.isNotEmpty())
            showHide(fragments[showIndex], fragments)
    }

    fun showHide(showFragment: Fragment, fragments: ArrayList<BaseFragment>) {
        if (fragments.isNotEmpty())
            showHide(showFragment, fragments.toTypedArray())
    }

    fun showHide(showFragment: Fragment, fragments: Array<BaseFragment>) {
        if (fragments.isNotEmpty()) {
            val fragmentManager = showFragment.fragmentManager
            fragmentManager?.beginTransaction()?.apply {
                fragments.forEach { fragment ->
                    if (fragment == showFragment) {
                        show(fragment)
                        setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                    } else {
                        hide(fragment)
                        setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                    }
                }
            }?.commit()
        }
    }

}