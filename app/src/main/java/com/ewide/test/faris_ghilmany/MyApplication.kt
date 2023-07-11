package com.ewide.test.faris_ghilmany

import android.app.Application
import com.ewide.test.faris_ghilmany.core.di.databaseModule
import com.ewide.test.faris_ghilmany.core.di.networkModule
import com.ewide.test.faris_ghilmany.core.di.repositoryModule
import com.ewide.test.faris_ghilmany.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}