package dependencies

object SqlDelight {
    val android = Android
    val ios = Ios
    const val coroutines = "com.squareup.sqldelight:coroutines-extensions:${Version.core}"
    const val runtime = "com.squareup.sqldelight:runtime:${Version.core}"

    object Android {
        const val driver = "com.squareup.sqldelight:android-driver:${Version.core}"
    }

    object Ios {
        const val driver = "com.squareup.sqldelight:native-driver:${Version.core}"
    }

    private object Version {
        const val core = "1.5.3"
    }
}