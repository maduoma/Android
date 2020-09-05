package com.dodemy.gadsleaderboardaad.di


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.dodemy.gadsleaderboardaad.network.ApiService
import com.dodemy.gadsleaderboardaad.network.SubmissionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }


    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "https://gadsapi.herokuapp.com/"
    }

    @Provides
    @Named("submissionUrl")
    fun provideSubmissionUrl(): String {
        return "https://docs.google.com/forms/d/e/"
    }


    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi, @Named("baseUrl") baseUrl: String): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSubmissionService(
        moshi: Moshi,
        @Named("submissionUrl") baseUrl: String
    ): SubmissionService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SubmissionService::class.java)
    }


}
