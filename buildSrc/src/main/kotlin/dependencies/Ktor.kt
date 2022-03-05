package dependencies

object Ktor {
    private const val group = "io.ktor"
    const val clientCore = "$group:ktor-client-core:${Version.ktor}"
    const val clientSerialization = "$group:ktor-client-serialization:${Version.ktor}"
    const val clientLogging = "$group:ktor-client-logging:${Version.ktor}"
    val android = Android
    val ios = Ios

    object Android {
        const val client = "$group:ktor-client-android:${Version.ktor}"
        const val logging = "$group:ktor-client-logging-jvm:${Version.ktor}"
    }

    object Ios {
        const val client = "$group:ktor-client-ios:${Version.ktor}"
        val arm64 = Arm64
        val x64 = X64

        object Arm64 {
            const val logging = "$group:ktor-client-logging-iosarm64:${Version.ktor}"
        }

        object X64 {
            const val logging = "$group:ktor-client-logging-iosx64:${Version.ktor}"
        }
    }

    private object Version {
        const val ktor = "1.6.7"
    }
}