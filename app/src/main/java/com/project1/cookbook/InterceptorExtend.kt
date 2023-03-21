package com.project1.cookbook

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class InterceptorExtend : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.spoonacular.com/recipes/complexSearch")
            .get()
            .addHeader("x-api-key", "2409149156a54d3a84931bbac4f6f168")
            .build()


        return try {
            client.newCall(request).execute()
        } catch (e: Exception) {
            Log.d("Interceptor", "ERROR ${e.message}")
            throw e
        }
    }
}