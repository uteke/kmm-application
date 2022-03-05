package com.uteke.kmm.android

import android.app.Application
import com.uteke.kmm.di.initDependencies
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext

@FlowPreview
@Suppress("unused")
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencies { koinApplication ->
            koinApplication.apply {
                androidContext(this@Application)
            }
        }
    }
}