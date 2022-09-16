import io.digikraft.buildsrc.Configs
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android-extensions")
    id("com.google.protobuf") version io.digikraft.buildsrc.libraries.Versions.protobuf_plugin
    id("kotlinx-serialization")
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://digikraft-validate.herokuapp.com/api/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
    kapt {
        generateStubs = true
    }
    sourceSets["main"].java.srcDir("src/main/kotlin")
}

dependencies {

    implementation(project(ProjectRootLibraries.utils))
    requiredLibraries()
    supportLibraries()
    networkLibraries()
    roomLibraries()
    dataStoreLibraries()
    firebaseLibraries()
    facebookLibraries()
    cryptoLibraries()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.2"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}