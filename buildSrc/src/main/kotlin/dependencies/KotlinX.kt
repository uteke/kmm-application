package dependencies

object KotlinX {
    private const val group = "org.jetbrains.kotlinx"
    val android = Android
    val ios = Ios
    const val coroutine = "$group:kotlinx-coroutines-core:${Version.coroutine}"
    const val coroutineVersion = Version.coroutine

    object Android {
        const val serialization = "$group:kotlinx-serialization-core-jvm:${Version.serialization}"
    }

    object Ios {
        val arm64 = Arm64
        val x64 = X64

        object Arm64 {
            const val serialization = "$group:kotlinx-serialization-core-iosarm64:${Version.serialization}"
        }

        object X64 {
            const val serialization = "$group:kotlinx-serialization-core-iosx64:${Version.serialization}"
        }
    }

    private object Version {
        const val serialization = "1.3.2"
        const val coroutine = "1.6.0-native-mt"
    }
}