plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "net.ezlotest.domain"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}