package com.mrigankprojects.myretrofit_rx.api

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

class OkHttpProduction {
    fun OkHttpProduction() = Unit

    companion object getOkHttpClient {
        val DISK_CACHE_SIZE = 10 * 1024 * 1024 // 10MB
        fun getOkHttpClient(context: Context, debug: Boolean): OkHttpClient {
            // Install an HTTP cache in the context cache directory.
            val cacheDir = File(context.cacheDir, "http")
            val cache = Cache(cacheDir, DISK_CACHE_SIZE.toLong())

            val builder = OkHttpClient.Builder().cache(cache)
            builder.connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES)

            if (debug) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }
            return builder.build()
        }
    }

}