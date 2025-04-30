// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
}

val localProperties = java.util.Properties().apply {
    file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}

val tmdbApiKey: String = localProperties.getProperty("tmdb.api.key")
val tmdbAccountId: String = localProperties.getProperty("tmdb.account.id")

allprojects {
    extra["TMDB_API_KEY"] = tmdbApiKey
    extra["TMDB_ACCOUNT_ID"] = tmdbAccountId
}
