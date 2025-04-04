package com.bypriyan.togocartstore.DI.module

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import com.google.gson.Gson
import com.socialseller.ceo.api.ApiAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://erpcopy.bulkbroadcast.com/api/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiAuth(retrofit: Retrofit): ApiAuth {
        return retrofit.create(ApiAuth::class.java)
    }
//
//    @Provides
//    @Singleton
//    fun provideApiProducts(retrofit: Retrofit): ApiProducts {
//        return retrofit.create(ApiProducts::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiAddress(retrofit: Retrofit): ApiAddress {
//        return retrofit.create(ApiAddress::class.java)
//    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

}