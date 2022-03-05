package com.uteke.kmm.feature.common

import kotlinx.coroutines.CoroutineDispatcher

interface SchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
}