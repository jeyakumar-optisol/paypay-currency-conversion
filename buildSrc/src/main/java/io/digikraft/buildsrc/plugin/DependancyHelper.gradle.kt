import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.requiredLibraries() {
    api(RequiredLibraries.kotlinStdLib)
    api(RequiredLibraries.core_ktx)
    api(RequiredLibraries.coroutines_android)
    api(RequiredLibraries.anko)
    api(RequiredLibraries.anko_commons)
    api(RequiredLibraries.coroutines_core)
    api(RequiredLibraries.coroutines_test)
    implementation(RequiredLibraries.json_serializer)
    api(RequiredLibraries.lifecycle_extension)
    api(RequiredLibraries.viewbinding)
    api(RequiredLibraries.multi_dex)
    api(RequiredLibraries.gson)
    api(RequiredLibraries.timber)
    api(RequiredLibraries.runtime_ktx)
    api(RequiredLibraries.hilt_android)
    kapt(RequiredLibraries.kapt_hilt_android_compiler)
    kapt(RequiredLibraries.kapt_hilt_compiler)
    api(RequiredLibraries.databinding_runtime)
    api(RequiredLibraries.viewmodel_ktx)
    api(RequiredLibraries.livedata_ktx)
}

fun DependencyHandler.supportLibraries() {
    implementation(SupportLibraries.appCompat)
    implementation(SupportLibraries.support_design)
    implementation(SupportLibraries.constraintLayout)
    implementation(SupportLibraries.sdp_android)
    implementation(SupportLibraries.ssp_android)
    implementation(SupportLibraries.material)
    implementation(SupportLibraries.recyclerview)
    implementation(SupportLibraries.card_view)
    implementation(SupportLibraries.legacy_support)
    implementation(SupportLibraries.paging_runtime)
    implementation(SupportLibraries.viewpager2)
    implementation(SupportLibraries.activity_ktx)
    implementation(SupportLibraries.fragment_ktx)
    implementation(SupportLibraries.navigation_fragment_ktx)
    implementation(SupportLibraries.navigation_ui_ktx)
    implementation(SupportLibraries.splash_screen)
}

fun DependencyHandler.imageLoaderLibraries() {
    implementation(ImageLoaderLibraries.glide)
    implementation(ImageLoaderLibraries.glide_integration)
    implementation(ImageLoaderLibraries.glide_transformations)
    kapt(ImageLoaderLibraries.kapt_glide_integration)
}


fun DependencyHandler.networkLibraries() {
    api(NetworkLibraries.retrofit)
    api(NetworkLibraries.retrofit_converter_gson)
    api(NetworkLibraries.okhttp)
    api(NetworkLibraries.okhttp_logging_interceptor)
    debugImplementation(NetworkLibraries.chucker_debug)
    releaseImplementation(NetworkLibraries.chucker_release)
}

fun DependencyHandler.dateTimeLibraries() {
    api(DateTimeLibraries.joda_time)
    api(DateTimeLibraries.joda_convert)
}

fun DependencyHandler.roomLibraries() {
    implementation(RoomLibraries.room_runtime)
    implementation(RoomLibraries.room_ktx)
    implementation(RoomLibraries.room_paging)
    kapt(RoomLibraries.kapt_room_compiler)
}

fun DependencyHandler.dataStoreLibraries() {
    api(DataStoreLibraries.protobuf)
    api(DataStoreLibraries.data_store)
    api(DataStoreLibraries.data_store_preferences)
    api("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3")
}

fun DependencyHandler.testLibraries() {
    implementation(TestLibraries.espressoCore)
    implementation(TestLibraries.junit)
    implementation(TestLibraries.junitTest)

    androidTestImplementation(TestLibraries.androidTestImplementationRobolectric)
    androidTestAnnotationProcessor(TestLibraries.androidTestAnnotationProcessor)
    kaptAndroidTest(TestLibraries.kaptAndroidTest)
    testAnnotationProcessor(TestLibraries.testAnnotationProcessorHiltAndroidTesting)
    androidTestImplementation(TestLibraries.androidTestImplementationHiltAndroidTesting)
    testImplementation(TestLibraries.testImplementationHiltAndroidTesting)
}

fun DependencyHandler.firebaseLibraries() {
    implementation(FirebaseLibraries.google_play_services)
    implementation(FirebaseLibraries.firebase_dynamic_links)
    implementation(FirebaseLibraries.firebase_authentication)
    implementation(FirebaseLibraries.firebase_crashlytics)
    implementation(FirebaseLibraries.firebase_config)
    implementation(FirebaseLibraries.firebase_analytics)
    implementation(FirebaseLibraries.firebase_core)
    implementation(FirebaseLibraries.firebase_messaging)
    implementation(FirebaseLibraries.playservices_coroutines)
    implementation("com.firebaseui:firebase-ui-auth:7.2.0")
}

fun DependencyHandler.mapsLibraries() {
    implementation(MapLibraries.maps)
}

fun DependencyHandler.thirdPartyLibraries() {
    implementation(ThirdPartyLibraries.lottie)
    implementation("com.tbuonomo:dotsindicator:4.3")
    implementation("com.github.abdulrehmank7:iOS-date-time-picker:1.05")
}

fun DependencyHandler.facebookLibraries() {
    implementation("com.facebook.android:facebook-android-sdk:latest.release")
}

fun DependencyHandler.cryptoLibraries() {
    implementation("com.google.crypto.tink:tink-android:1.6.1")
}