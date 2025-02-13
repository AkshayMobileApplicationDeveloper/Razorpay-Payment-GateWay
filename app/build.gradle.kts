plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.app.razorpaypaymentgateway"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.razorpaypaymentgateway"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
repositories {
    mavenCentral()
}
/*
External
 */
dependencies {
    implementation ("com.github.skydoves:elasticviews:2.1.0")
    implementation ("com.razorpay:checkout:1.6.38")
}
// settings.gradle.kts



