package com.example.pagingroomdbmvvm.data.remote

import com.example.pagingroomdbmvvm.data.model.News
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NewsApi {

    companion object {
        private const val BASE_URL = "https://news.soolegal.com/api/index.php/"

        operator fun invoke(): NewsApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @FormUrlEncoded
    @POST("LGLNews")
    suspend fun getNewsResult(
        @Field("page")page:Int
    ): News
}