@Suppress("UnstableApiUsage")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.android.library)
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    @Suppress("UnstableApiUsage")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.client.serialization)
                implementation(libs.sqldelight.coroutines)
                implementation(libs.stately)
            }
        }
        getByName("androidMain") {
            dependencies {
                implementation(libs.kotlinx.serialization.jvm)
                implementation(libs.koin.android)
                implementation(libs.ktor.client.android)
                implementation(libs.ktor.client.logging.jvm)
                implementation(libs.ktor.client.contentnegotiation.jvm)
                implementation(libs.sqldelight.android.driver)
            }
        }
        val iosX64Main by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.iosx64)
                implementation(libs.ktor.client.logging.iosx64)
                implementation(libs.ktor.client.contentnegotiation.iosx64)
                implementation(libs.sqldelight.native.driver.iosx64)
            }
        }
        val iosArm64Main by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.iosarm64)
                implementation(libs.ktor.client.logging.iosarm64)
                implementation(libs.ktor.client.contentnegotiation.iosarm64)
                implementation(libs.sqldelight.native.driver.iosarm64)
            }
        }
        val iosSimulatorArm64Main by getting
        create("iosMain") {
            dependencies {
                implementation(libs.ktor.client.ios)
                implementation(libs.kotlinx.coroutines.core)
            }

            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        getByName("androidTest") {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        create("iosTest") {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = AppConfig.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.uteke.kmm.storage.sqldelight"
    }
}