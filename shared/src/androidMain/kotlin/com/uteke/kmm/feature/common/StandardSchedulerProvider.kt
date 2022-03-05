package com.uteke.kmm.feature.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class StandardSchedulerProvider : SchedulerProvider {
    actual override fun io(): CoroutineDispatcher = Dispatchers.IO
    actual override fun ui(): CoroutineDispatcher = Dispatchers.Main
}