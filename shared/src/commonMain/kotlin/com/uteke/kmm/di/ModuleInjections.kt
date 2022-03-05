package com.uteke.kmm.di

import kotlinx.coroutines.FlowPreview
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

@Suppress("unused")
@FlowPreview
fun initDependencies() = initDependencies { it }

@FlowPreview
fun initDependencies(build: (application: KoinApplication) -> KoinApplication) {
    startKoin {
        build(
            modules(
                networkModule,
                storageModule,
                commonModule,
                productListModule,
                productItemModule
            )
        )
    }
}
