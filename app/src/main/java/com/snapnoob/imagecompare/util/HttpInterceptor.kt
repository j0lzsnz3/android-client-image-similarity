package com.snapnoob.imagecompare.util

import android.content.Context
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.snapnoob.imagecompare.BuildConfig
import okhttp3.*
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit


/**
 * Created by @ImamKR97
 **/
object HttpIntercept {

    fun getOkHttp(context: Context) : OkHttpClient {
        val okhttpBuilder = OkHttpClient().newBuilder()
        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(context.cacheDir, cacheSize.toLong())
        okhttpBuilder.cache(cache)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        okhttpBuilder.addInterceptor(interceptor)
        okhttpBuilder.addInterceptor(GetInterceptor.HeaderInterceptor())

        return okhttpBuilder.build()
    }

    class GetInterceptor {

        internal class HeaderInterceptor : Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val ongoing = chain.request().newBuilder()
                ongoing.addHeader("Accept", "application/json")
                ongoing.addHeader("Content-Type", "application/json")
                val newRequest = ongoing.build()
                return onOnIntercept(chain, newRequest)
            }

            @Throws(IOException::class)
            private fun onOnIntercept(chain: Interceptor.Chain, request: Request): Response {
                return try {
                    chain.proceed(request)
                } catch (e: SocketTimeoutException) {
                    val response = chain.proceed(chain.request())
                    response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), "Host tidak ditemukan")).build()
                } catch (e: UnknownHostException) {
                    val response = chain.proceed(chain.request())
                    response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), "Host tidak ditemukan")).build()
                }
            }
        }
    }
}
