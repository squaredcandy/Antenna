package com.squaredcandy.antenna.util

import android.app.Application
import logcat.AndroidLogcatLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AntennaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this)

        startKoin {
            androidContext(this@AntennaApplication)
            androidLogger(Level.DEBUG)
        }
    }
}