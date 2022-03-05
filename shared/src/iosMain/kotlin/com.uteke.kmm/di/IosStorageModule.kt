package com.uteke.kmm.di

import com.uteke.kmm.storage.sqldelight.AppDatabase
import com.uteke.kmm.storage.sqldelight.DatabaseDriverFactory
import org.koin.core.scope.Scope

actual fun Scope.createAppDatabase(): AppDatabase =
    AppDatabase.invoke(driver = DatabaseDriverFactory().createDriver())