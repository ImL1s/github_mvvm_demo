// progressive kotlin DSL :D

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.future.github"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")

    // recycleview
    val recyclerviewVersion = "1.0.0"
    implementation("androidx.recyclerview:recyclerview:$recyclerviewVersion")

    // cardview
    val cardviewVersion = "1.0.0"
    implementation("androidx.cardview:cardview:$cardviewVersion")

    // retrofit
    val retrofitVersion = "2.4.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:3.10.0")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // okhttp
    val okhttp3Version = "3.12.1"
    implementation("com.squareup.okhttp3:okhttp:$okhttp3Version")

    // RxJava
    val rxVersion = "3.0.0-RC4"
    val rxFullVersion = "rxjava3:rxjava:$rxVersion"
    implementation("io.reactivex.$rxFullVersion")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:$rxVersion")
//    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")
//    implementation("io.reactivex.rxjava3:rxandroid:$rx_version")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1") {
        exclude(group = "io.reactivex.rxjava2", module = "rxjava")
    }
    implementation("io.reactivex.$rxFullVersion")
    implementation("com.github.akarnokd:rxjava3-android-interop:3.0.0-RC4")

    // network image
    val glideVersion = "4.10.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    // dagger2
    implementation("com.google.dagger:dagger-android:2.15")
    implementation("com.google.dagger:dagger-android-support:2.15")
    annotationProcessor("com.google.dagger:dagger-android-processor:2.15")
    annotationProcessor("com.google.dagger:dagger-compiler:2.15")
    kapt("com.google.dagger:dagger-compiler:2.15")
//    implementation("androidx.multidex:multidex:2.0.0")

    // circle avatar
    val circleAvatarVersion = "3.0.1"
    implementation("de.hdodenhof:circleimageview:$circleAvatarVersion")
}
