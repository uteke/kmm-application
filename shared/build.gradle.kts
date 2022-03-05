plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.koin.core)
                implementation(Dependencies.kotlinX.coroutine)
                implementation(Dependencies.ktor.clientCore)
                implementation(Dependencies.ktor.clientLogging)
                implementation(Dependencies.ktor.clientSerialization)
                implementation(Dependencies.sqlDelight.coroutines)
                implementation(Dependencies.sqlDelight.runtime)
                implementation(Dependencies.touchLab.stately)
            }
        }
        getByName("androidMain") {
            dependencies {
                implementation(Dependencies.kotlinX.android.serialization)
                implementation(Dependencies.koin.android)
                implementation(Dependencies.ktor.android.client)
                implementation(Dependencies.ktor.android.logging)
                implementation(Dependencies.sqlDelight.android.driver)
            }
        }
        val iosX64Main by getting {
            dependencies {
                implementation(Dependencies.kotlinX.ios.x64.serialization)
                implementation(Dependencies.ktor.ios.x64.logging)
            }
        }
        val iosArm64Main by getting {
            dependencies {
                implementation(Dependencies.kotlinX.ios.arm64.serialization)
                implementation(Dependencies.ktor.ios.arm64.logging)
            }
        }
        val iosSimulatorArm64Main by getting
        create("iosMain") {
            dependencies {
                implementation(Dependencies.ktor.ios.client)
                implementation(Dependencies.sqlDelight.ios.driver)
                implementation(Dependencies.kotlinX.coroutine) {
                    version {
                        strictly(Dependencies.kotlinX.coroutineVersion)
                    }
                }
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