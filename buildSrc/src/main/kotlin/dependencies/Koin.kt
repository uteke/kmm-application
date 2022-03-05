package dependencies

object Koin {
    private const val group = "io.insert-koin"
    const val android = "$group:koin-android:${Versions.koin}"
    const val androidExt = "$group:koin-android-ext:${Versions.koin}"
    const val androidCompose = "$group:koin-androidx-compose:${Versions.koin}"
    const val core = "$group:koin-core:${Versions.koin}"
    const val ktor = "$group:koin-ktor:${Versions.koin}"

    private object Versions {
        const val koin = "3.1.5"
    }
}