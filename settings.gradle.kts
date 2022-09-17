//pluginManagement {
//    repositories {
//        gradlePluginPortal()
//        mavenCentral()
//        jcenter()
//        google()
//        maven { url = "https://www.jitpack.io" }
//        maven { url = "https://plugins.gradle.org/m2/"}
//    }
//}
//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories {
//        jcenter()
//        google()
//        mavenCentral()
//        maven {
//            url = "https://www.jitpack.io"
//        }
//    }
//}
rootProject.name = "Photosapp"
include(
    ":app",
    ":shared",
    ":utils")