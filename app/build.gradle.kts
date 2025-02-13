plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // TODO 2: hilt - import hilt plugins in module's build.gradle
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    // TODO 19: serialization - add the following plugins in app's build.gradle
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.unitedmania_jetpackcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.unitedmania_jetpackcompose"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        // TODO 11: di - make sure to enable buildConfig
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // TODO 3: hilt - import dependencies
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")
    // TODO 15: internet - import retrofit, gson and okhttp dependencies
    // retrofit, gson
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Okhttp3 logging interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.2")
    // TODO 17: import paging dependencies
    implementation("androidx.paging:paging-runtime:3.3.4")
    implementation("androidx.paging:paging-compose:3.3.4")
    // TODO 20: serialization - add the following dependency
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    // TODO 25: loading image - add the following dependency
    implementation("io.coil-kt:coil-compose:2.4.0")
}