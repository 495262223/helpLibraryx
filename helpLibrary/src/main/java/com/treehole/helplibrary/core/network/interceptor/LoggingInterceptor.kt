package com.treehole.helplibrary.core.network.interceptor

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

/**
 * 请求日志拦截器
 * 拦截
 * 发送请求时的地址,请求头,请求参数信息
 * 响应请求后的地址,请求头,请求响应时间
 */
class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val UTF8 = Charset.forName("UTF-8")
        val request = chain.request()
        val requestBody = request.body
        val buffer = Buffer()
        requestBody?.writeTo(buffer)
        val contentType = requestBody?.contentType()
        val charset = contentType?.charset(UTF8)
        val reqBody = charset?.let { buffer.readString(it) }
        val startTime = System.nanoTime()
        LogUtils.e(
            "Api Request",
            "Api Address : ${request.url}",
            "Api Headers : ${request.headers}",
            "Api Params : $reqBody"
        )
        val response = chain.proceed(request)
        val endTime = System.nanoTime()
        LogUtils.e(
            "Api Response",
            "Api Address : ${response.request.url}",
            "Api Headers : ${response.headers}",
            "Api Time : ${endTime - startTime} ms)"
        )
        return response
    }
}