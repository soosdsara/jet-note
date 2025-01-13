plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.jetnote"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.jetnote"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //Hilt-Dagger (Dependency Injection tool)
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    /*
        implementation "com.google.dagger:hilt-android:$hilt_version"
        kapt "com.google.dagger:hilt-compiler:$hilt_version"

    //Room
        implementation "androidx.room:room-runtime:$room_version"
        annotationProcessor "androidx.room:room-compiler:$room_version"

    // To use Kotlin annotation processing tool (kapt) MUST HAVE!
        kapt("androidx.room:room-compiler:$room_version")
        implementation "androidx.room:room-ktx:$room_version"


    // Coroutines
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
        implementation "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.2"*/
}

kapt {
    correctErrorTypes = true
}

