package io.davidosemwota.ulessontask

import android.app.Application
import timber.log.Timber

class UlessonApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}