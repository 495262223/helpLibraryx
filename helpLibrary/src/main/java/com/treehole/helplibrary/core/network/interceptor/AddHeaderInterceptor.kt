package com.treehole.helplibrary.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 示例
 * 拦截器
 * 给需要添加头部的请求添加头部
 * 类似添加 token 之类
 */
class AddHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequestBuilder = chain.request().newBuilder()
        newRequestBuilder.addHeader("auth", "value")
        return chain.proceed(newRequestBuilder.build())
    }

}