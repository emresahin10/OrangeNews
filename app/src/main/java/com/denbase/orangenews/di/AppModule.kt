package com.denbase.orangenews.di

import android.content.Context
import com.denbase.orangenews.api.NewsApi
import com.denbase.orangenews.repositories.MainNewsRepository
import com.denbase.orangenews.repositories.NewsRepository
import com.denbase.orangenews.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @ViewModelScoped
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context) = context

    @ViewModelScoped
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @ViewModelScoped
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @ViewModelScoped
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @ViewModelScoped
    @Provides
    fun provideApiService(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @ViewModelScoped
    @Provides
    fun provideRepository(api: NewsApi) = MainNewsRepository(api) as NewsRepository
}