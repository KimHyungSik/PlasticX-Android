package com.example.plasticx.retrofit.user

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.plasticx.MyApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRetrofitClient {
    private var retrofitClient: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        val client = OkHttpClient.Builder()

        val interceptor: Interceptor = (object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                val originalRequest = chain.request()

                val response = chain.proceed(originalRequest)
                if (response.code != 200) {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(
                            MyApplication.instance,
                            "${response.code} 에러입니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                return response
            }
        })

        client.addInterceptor(interceptor)

        if(retrofitClient == null){
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }

        return retrofitClient
    }
}