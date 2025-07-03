plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.watchlist.movies"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.watchlist.movies"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TMDB_BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "TMDB_API_KEY", rootProject.extra["TMDB_API_KEY"].toString())
        buildConfigField("String", "TMDB_IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kotlin {
    explicitApi()
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":data:movies"))
    implementation(project(":tmdb"))
    implementation(project(":database"))
    implementation(project(":feature:home"))
    implementation(project(":feature:movieDetails"))
    implementation(project(":feature:favorites"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}