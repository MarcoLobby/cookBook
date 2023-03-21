package com.project1.cookbook.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project1.cookbook.ApiServiceInterface
import com.project1.cookbook.InterceptorExtend
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val BASE_URL = "https://api.spoonacular.com"
    private val retrofit: Retrofit = provideRetrofit(
        client = provideOkHttpClient(loggingInterceptor = provideHttpLoggingInterceptor()),
        gson = provideGson()
    )

    private fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(InterceptorExtend())
            .build()
    }

    private fun provideGson(): Gson = GsonBuilder()
        .create()

    val apiServiceInterface: ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)
}
