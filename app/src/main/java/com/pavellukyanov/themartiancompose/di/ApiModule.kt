package com.pavellukyanov.themartiancompose.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pavellukyanov.themartiancompose.BuildConfig
import com.pavellukyanov.themartiancompose.data.api.services.RoverService
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {
    companion object {
        private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"
        private const val API_KEY_VALUE = "f8FYngXOCFmWPVOgmcugDO5JwAsPB238oee4wh6V"
        private const val API_KEY = "api_key"
        private const val LOG_TAG = "OkHttp"
        private const val TIMEOUT = 30L
    }

    private val contentType = "application/json".toMediaType()

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
            .apply {
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            }

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor { message -> Timber.tag(LOG_TAG).d(message) }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        }

        okHttpBuilder.addInterceptor {
            val request = it.request()
            val url = request.url
                .newBuilder()
                .addQueryParameter(API_KEY, API_KEY_VALUE)
                .build()
            it.proceed(request.newBuilder().url(url).build())
        }

        val json = Json {
            coerceInputValues = true
            explicitNulls = false
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideRoverService(retrofit: Retrofit): RoverService = retrofit.create(RoverService::class.java)
}