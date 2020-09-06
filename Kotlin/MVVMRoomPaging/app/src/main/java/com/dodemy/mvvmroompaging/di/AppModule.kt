package com.dodemy.mvvmroompaging.di

import android.app.Application
import com.dodemy.mvvmroompaging.BuildConfig
import com.dodemy.mvvmroompaging.api.AuthInterceptor
import com.dodemy.mvvmroompaging.api.LegoService
import com.dodemy.mvvmroompaging.data.AppDatabase
import com.dodemy.mvvmroompaging.legoset.data.LegoSetRemoteDataSource
import com.dodemy.mvvmroompaging.legotheme.data.LegoThemeRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideLegoService(@LegoAPI okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, LegoService::class.java)

    @Singleton
    @Provides
    fun provideLegoSetRemoteDataSource(legoService: LegoService)
            = LegoSetRemoteDataSource(legoService)

    @Singleton
    @Provides
    fun provideLegoThemeRemoteDataSource(legoService: LegoService)
            = LegoThemeRemoteDataSource(legoService)

    @LegoAPI
    @Provides
    fun providePrivateOkHttpClient(
            upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
                .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideLegoSetDao(db: AppDatabase) = db.legoSetDao()


    @Singleton
    @Provides
    fun provideLegoThemeDao(db: AppDatabase) = db.legoThemeDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(LegoService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
