import io.paypay.buildsrc.Configs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlinx-serialization")
}


android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "io.paypay.currency.CustomTestRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    buildTypes {
        debug {
            versionNameSuffix = "-debug"
        }
        release {
            versionNameSuffix = "-release"
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions += "client"
    productFlavors {
        create("development") {
            applicationId = "${Configs.applicationId}.development"
            dimension = "client"
            buildConfigField("String", "BASE_URL", "\"https://developmentserver.com/api/\"")
        }
        create("staging") {
            applicationId = "${Configs.applicationId}"
            dimension = "client"
            buildConfigField("String", "BASE_URL", "\"https://digikraft-validate.herokuapp.com/api/\"")
        }
        create("production") {
            applicationId = Configs.applicationId
            dimension = "client"
            buildConfigField("String", "BASE_URL", "\"https://release.com/api/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    kapt {
        correctErrorTypes=true
    }
    sourceSets["main"].java.srcDir("src/main/kotlin")
}

dependencies {

    implementation(project(ProjectRootLibraries.shared))
    implementation(project(ProjectRootLibraries.utils))
    testImplementation("org.junit.jupiter:junit-jupiter")

    requiredLibraries()
    supportLibraries()
//    imageLoaderLibraries()
    thirdPartyLibraries()
    mapsLibraries()
    firebaseLibraries()
    testLibraries()
    testImplementation(kotlin("test"))
}