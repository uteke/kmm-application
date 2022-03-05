package com.uteke.kmm.feature.common

import kotlinx.coroutines.CoroutineDispatcher

expect class StandardSchedulerProvider : SchedulerProvider {
    override fun io(): CoroutineDispatcher
    override fun ui(): CoroutineDispatcher
}