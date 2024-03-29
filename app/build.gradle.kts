plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp")
    id ("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.waffle.charismatic"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.waffle.charismatic"
        minSdk = 24
        targetSdk = 33
        versionCode = 2
        versionName = "1.1"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.0-beta02")
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha03")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // okhttp
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // viewmodel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // lifecycle
    implementation ("androidx.compose.runtime:runtime-livedata:1.4.7")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // koin
    implementation("io.insert-koin:koin-android:3.5.0")
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")

    // room
    implementation ("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")
    ksp ("androidx.room:room-compiler:2.5.2")

    // coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // navigation
    implementation ("androidx.navigation:navigation-compose:2.5.3")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    // remove bg
    implementation("com.github.erenalpaslan:removebg:1.0.4")

    // lottie
    implementation ("com.airbnb.android:lottie-compose:6.3.0")
}