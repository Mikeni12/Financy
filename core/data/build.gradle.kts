plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "mx.mikeni.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    testImplementation(project(":core:testing"))
    api(libs.kotlinx.coroutines.android)
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    api(libs.firebase.auth)
    api(libs.firebase.firestore)
    api(platform(libs.koin.bom))
    api(libs.koin.android)
    api(libs.koin.androidx.startup)
    api(libs.koin.androidx.compose)
    api(libs.koin.androidx.compose.navigation)
    implementation(libs.androidx.core.ktx)
}
