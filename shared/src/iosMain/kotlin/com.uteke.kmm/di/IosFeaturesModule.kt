package com.uteke.kmm.di

import com.uteke.kmm.feature.common.SchedulerProvider
import com.uteke.kmm.feature.common.StandardSchedulerProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val commonModule = module {
    singleOf(::StandardSchedulerProvider) bind SchedulerProvider::class
}