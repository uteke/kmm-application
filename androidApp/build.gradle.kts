plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.androidX.compose.compilerVersion
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Dependencies.koin.androidCompose)
    implementation(Dependencies.androidX.activity.compose)
    implementation(Dependencies.androidX.activity.core)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.androidX.compose.compiler)
    implementation(Dependencies.androidX.compose.foundation)
    implementation(Dependencies.androidX.compose.material3)
    implementation(Dependencies.androidX.compose.material)
    implementation(Dependencies.androidX.compose.runtime)
    implementation(Dependencies.androidX.compose.ui)
    implementation(Dependencies.androidX.compose.uiToolingPreview)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.navigation.compose)
    implementation(Dependencies.coil.compose)
    implementation(Dependencies.google.material)
}