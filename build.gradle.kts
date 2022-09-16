// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    `kotlin-dsl`
    id("com.google.dagger.hilt.android") version ("2.42") apply (false)
}

buildscript {

    repositories {
        mavenCentral()
        maven(url = "https://maven.google.com/")
//        gradlePluginPortal()
        jcenter()
        google()
    }
    dependencies {
        classpath(ProjectRootLibraries.classpathGradle)
        classpath(ProjectRootLibraries.classpathKotlinGradle)
        classpath(ProjectRootLibraries.classpathDaggerHiltVersion)
        classpath(ProjectRootLibraries.classPathGoogleService)
        classpath(ProjectRootLibraries.classPathFirebasePerfs)
        classpath(ProjectRootLibraries.classpathCrashlytics)
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.21")
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://maven.google.com/")
        maven(url = "https://jitpack.io")
        jcenter()
        google()
    }
}

//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}