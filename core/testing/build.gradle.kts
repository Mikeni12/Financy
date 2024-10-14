plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "mx.mikeni.testing"
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
    packaging {
        resources.excludes.addAll(listOf("META-INF/LICENSE.md", "META-INF/LICENSE-notice.md"))
    }
}

dependencies {
    val mockkVersion = "1.13.13"
    api("io.mockk:mockk-android:${mockkVersion}")
    api("io.mockk:mockk-agent:${mockkVersion}")
    api(libs.mockk.android)
    api(libs.mockk.agent)
    api("org.mockito.kotlin:mockito-kotlin:4.1.0")
    api(libs.kotlinx.coroutines.android)
    api(libs.kotlinx.coroutines.test)
    api(libs.androidx.junit)
}
