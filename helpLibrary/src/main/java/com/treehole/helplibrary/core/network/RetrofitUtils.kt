package com.treehole.helplibrary.core.network

import com.treehole.helplibrary.core.network.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 一个网络请求的封装模板
 */
class RetrofitUtils {

    companion object {
        // 请求地址,可设置多个不同的请求地址
        private const val BASE_URL1 = "xxx"

        // 连接超时时间
        private const val CONNECT_TIME = 60L

        // 写入超时时间
        private const val WRITE_TIME = 60L

        // 读取超时时间
        private const val READ_TIME = 60L
    }

    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
            .readTimeout(READ_TIME, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor())
            .build()

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun <T> create(apiService: Class<T>): T = retrofit.create(apiService)

    inline fun <reified T> create(): T = create(T::class.java)

}