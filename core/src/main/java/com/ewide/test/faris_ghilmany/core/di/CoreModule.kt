package com.ewide.test.faris_ghilmany.core.di

import androidx.room.Room
import com.ewide.test.faris_ghilmany.core.BuildConfig
import com.ewide.test.faris_ghilmany.core.data.DisneyRepository
import com.ewide.test.faris_ghilmany.core.data.source.local.LocalDataSource
import com.ewide.test.faris_ghilmany.core.data.source.local.room.DisneyDatabase
import com.ewide.test.faris_ghilmany.core.data.source.remote.RemoteDataSource
import com.ewide.test.faris_ghilmany.core.data.source.remote.network.DisneyApiService
import com.ewide.test.faris_ghilmany.core.domain.repository.IDisneyRepository
import com.ewide.test.faris_ghilmany.core.utils.AppExecutors
import com.ewide.test.faris_ghilmany.core.utils.PreferenceProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<DisneyDatabase>().disneyDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DisneyDatabase::class.java, "DATABASE_NAME"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()


    }

    single {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(DisneyApiService::class.java)
    }

}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IDisneyRepository> {
        DisneyRepository(get(), get(), get())
    }
    single { PreferenceProvider(get()) }
}