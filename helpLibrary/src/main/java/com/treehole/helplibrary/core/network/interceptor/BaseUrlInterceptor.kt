package com.treehole.helplibrary.core.network.interceptor

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 示例
 * 设置多个 BASE_URL 时用到
 */
class BaseUrlInterceptor : Interceptor {

    companion object {
        private const val BASE_URL2 = "需要切换的地址"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        // 获取请求实例
        val request = chain.request()
        /* 获取所有接口设置的请求头部,
         * 例如:
         *  @Headers("urlName:url2")
         *  @POST("api/apiName")
         *  suspend fun bindingWallet()
         */
        val headerValues = request.headers("urlName")
        if (headerValues.isNotEmpty()) {
            // 获取第 0 个 Header 元素值
            val headerValue = headerValues[0]
            // 进行判断比较是否来切换网络请求地址
            val newHttpUrl = if (headerValue == "url2") {
                // 将地址转换为 HttpUrl
                BASE_URL2.toHttpUrlOrNull()
            } else {
                null
            }
            if (newHttpUrl != null) {
                // 创建一个新的请求,传入切换的网络地址 HttpUrl
                val newRequest = request.newBuilder().url(newHttpUrl).build()
                return chain.proceed(newRequest)
            }
        }
        return chain.proceed(request)
    }

}