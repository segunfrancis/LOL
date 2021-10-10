package com.segunfrancis.lol

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.segunfrancis.lol.di.dispatcherModule
import com.segunfrancis.lol.di.remoteModule
import com.segunfrancis.lol.di.repoModule
import com.segunfrancis.lol.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class LolApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@LolApplication)
            modules(remoteModule, dispatcherModule, repoModule, viewModelModule)
        }
        // Initialize Google adMob
        MobileAds.initialize(this) {}
        setupTestDevice()
    }

    private fun setupTestDevice() {
        // Enable test ads
        val request = RequestConfiguration.Builder()
            .setTestDeviceIds(listOf("8352585357BD24FD03F59A5EDAB92679")).build()
        MobileAds.setRequestConfiguration(request)
    }
}
