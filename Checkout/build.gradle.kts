plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.ingresse.checkout"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        aarMetadata {
            minCompileSdk = 26
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Adjust version if needed
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") { // You can choose a different name
            groupId = "com.github.dell2duo" // Replace with your domain or organization
            artifactId = "checkout" // Replace with your library's name
            version = "0.0.1-alpha.4" // Replace with your library's version
            artifact("build/outputs/aar/Checkout-release.aar")
        }
    }
}



dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.compose.ui:ui:1.6.8")            // Jetpack Compose UI
    implementation("androidx.compose.material:material:1.6.8")  // Material Design
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.8") // Preview support
}