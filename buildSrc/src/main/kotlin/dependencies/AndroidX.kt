package dependencies

object AndroidX {
    val activity = Activity
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    val compose = Compose
    const val core = "androidx.core:core-ktx:${Version.core}"
    val navigation = Navigation

    object Activity {
        const val core = "androidx.activity:activity-ktx:${Version.activity}"
        const val compose = "androidx.activity:activity-compose:${Version.activity}"
    }

    object Compose {
        const val compilerVersion = Version.compose
        const val compiler = "androidx.compose.compiler:compiler:${Version.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Version.compose}"
        const val material = "androidx.compose.material:material:${Version.compose}"
        const val material3 = "androidx.compose.material3:material3:${Version.composeMaterial3}"
        const val runtime = "androidx.compose.runtime:runtime:${Version.compose}"
        const val ui = "androidx.compose.ui:ui:${Version.compose}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:${Version.navigation}"
    }

    private object Version {
        const val activity = "1.4.0"
        const val appCompat = "1.4.1"
        const val compose = "1.1.0-rc03"
        const val core = "1.7.0"
        const val composeMaterial3 = "1.0.0-alpha02"
        const val material = "1.3.0"
        const val navigation = "2.4.0"
    }
}