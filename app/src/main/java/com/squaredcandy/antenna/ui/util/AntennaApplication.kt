package com.squaredcandy.antenna.ui.util

import android.app.Application
import com.squaredcandy.antenna.ui.di.RealKoinModule
import kotlinx.coroutines.MainScope
import logcat.AndroidLogcatLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AntennaApplication : Application() {

    private val appCoroutineScope = MainScope()

    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this)

        startKoin {
            androidContext(this@AntennaApplication)
            androidLogger(Level.DEBUG)
            modules(RealKoinModule.allModules(appCoroutineScope))
        }
    }
}