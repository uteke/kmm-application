package com.uteke.kmm.feature.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.CoroutineContext

actual class StandardSchedulerProvider : SchedulerProvider {
    actual override fun io(): CoroutineDispatcher = MainDispatcher
    actual override fun ui(): CoroutineDispatcher = MainDispatcher
}

object MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock { block.run() }
    }
}