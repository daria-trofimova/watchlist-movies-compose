plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }

    val tmdbApiKey = rootProject.extra["TMDB_API_KEY"] as String
    val tmdbAccountId = rootProject.extra["TMDB_ACCOUNT_ID"]

    val outputDir = layout.buildDirectory.dir("generated/source/kotlin").get().asFile
    val outputFile = outputDir.resolve("Secrets.kt")

    // Task to generate the Secrets.kt file
    tasks.register("generateSecrets") {
        doLast {
            outputDir.mkdirs()
            outputFile.writeText(
                """
                const val TMDB_API_KEY = $tmdbApiKey
                const val TMDB_ACCOUNT_ID = $tmdbAccountId
                """.trimIndent()
            )
        }
    }
    sourceSets.getByName("main").kotlin.srcDir(outputDir)
}

// Ensure the secrets generation task runs before Kotlin compilation
tasks.named("compileKotlin") {
    dependsOn("generateSecrets")
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.annotation)
    implementation(libs.kotlinx.coroutines.core)
}
