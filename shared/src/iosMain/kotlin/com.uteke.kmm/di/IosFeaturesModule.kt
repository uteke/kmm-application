package com.uteke.kmm.di

import com.uteke.kmm.feature.common.SchedulerProvider
import com.uteke.kmm.feature.common.StandardSchedulerProvider
import org.koin.dsl.module

actual val commonModule = module {
    single<SchedulerProvider> { StandardSchedulerProvider() }
}