import io.paypay.buildsrc.Configs
import io.paypay.buildsrc.libraries.Versions

object ProjectRootLibraries {
    const val classpathGradle =
        "com.android.tools.build:gradle:${Configs.classpathGradleVersion}"
    const val classpathKotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Configs.kotlinVersion}"
    const val classpathDaggerHiltVersion =
        "com.google.dagger:hilt-android-gradle-plugin:${Configs.classpathDaggerHiltVersion}"
    const val classPathGoogleService =
        "com.google.gms:google-services:${Configs.classpathGoogleServices}"
    const val classPathFirebasePerfs =
        "com.google.firebase:perf-plugin:${Configs.classpathFirebasePerfs}"
    const val classpathCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Configs.classpathCrashlytics}"
    const val utils = ":utils"
    const val shared = ":shared"
}

object RequiredLibraries {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Configs.kotlinVersion}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"
    const val coroutines_play_services =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_play_services}"
    const val json_serializer =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.json_serializer}"
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"
    const val anko_commons = "org.jetbrains.anko:anko-commons:${Versions.anko_commons}"
    const val lifecycle_extension =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extension}"
    const val multi_dex = "androidx.multidex:multidex:${Versions.multi_dex}"
    const val viewbinding = "com.android.databinding:viewbinding:${Versions.view_binding}"
    const val runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtime_ktx}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_android}"
    const val kapt_hilt_android_compiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hilt_android_compiler}"
    const val kapt_hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_compiler}"
    const val databinding_runtime =
        "androidx.databinding:databinding-runtime:${Versions.databinding_runtime}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object SupportLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val support_design = "com.android.support:design:${Versions.support_design}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    const val sdp_android = "com.intuit.sdp:sdp-android:${Versions.sdp_android}"
    const val ssp_android = "com.intuit.ssp:ssp-android:${Versions.ssp_android}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val card_view = "androidx.cardview:cardview:${Versions.card_view}"
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    const val paging_runtime = "androidx.paging:paging-runtime:${Versions.paging_runtime}"
    const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    const val navigation_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_fragment_ktx}"
    const val navigation_ui_ktx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation_fragment_ktx}"
    const val splash_screen = "androidx.core:core-splashscreen:${Versions.splash_screen}"
}

object NetworkLibraries {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val chucker_debug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chucker_release = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
}

object FirebaseLibraries {
    const val google_play_services = "com.google.android.gms:play-services-auth:20.2.0"
    const val firebase_messaging =
        "com.google.firebase:firebase-messaging:${Versions.firebase_messaging}"
    const val firebase_core = "com.google.firebase:firebase-core:${Versions.firebase_core}"
    const val firebase_analytics =
        "com.google.firebase:firebase-analytics:${Versions.firebase_analytics}"
    const val firebase_config = "com.google.firebase:firebase-config:${Versions.firebase_config}"
    const val firebase_authentication =
        "com.google.firebase:firebase-auth:${Versions.firebase_authentication}"
    const val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics:${Versions.firebase_crashlytics}"
    const val firebase_dynamic_links =
        "com.google.firebase:firebase-dynamic-links:${Versions.firebase_dynamic_links}"
    const val playservices_coroutines =
        "com.google.firebase:firebase-dynamic-links:${Versions.playservices_coroutines}"
}

object DateTimeLibraries {
    const val joda_time = "joda-time:joda-time:${Versions.joda_time}"
    const val joda_convert = "org.joda:joda-convert:${Versions.joda_convert}"
}

object RoomLibraries {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room_runtime}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_ktx}"
    const val room_paging = "androidx.room:room-paging:${Versions.room_paging}"
    const val kapt_room_compiler = "androidx.room:room-compiler:${Versions.room_runtime}"
}

object DataStoreLibraries {
    const val data_store = "androidx.datastore:datastore:${Versions.data_store}"
    const val data_store_preferences =
        "androidx.datastore:datastore-preferences:${Versions.data_store}"
    const val protobuf = "com.google.protobuf:protobuf-javalite:${Versions.protobuf}"
}

object ImageLoaderLibraries {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_transformations =
        "jp.wasabeef:glide-transformations:${Versions.glide_transformations}"
    const val glide_integration =
        "com.github.bumptech.glide:okhttp3-integration:${Versions.glide_integration}"
    const val kapt_glide_integration = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junitTest = "androidx.test.ext:junit:${Versions.junitTestVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val androidTestImplementationRobolectric =
        "org.robolectric:robolectric:4.4" //androidTestImplementation
    const val testImplementationHiltAndroidTesting =
        "com.google.dagger:hilt-android-testing:${Versions.hiltAndroidTesting}" //testImplementation
    const val androidTestImplementationHiltAndroidTesting =
        "com.google.dagger:hilt-android-testing:${Versions.hiltAndroidTesting}" //androidTestImplementation
    const val testAnnotationProcessorHiltAndroidTesting =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}" //testAnnotationProcessor
    const val kaptAndroidTest =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}" //kaptAndroidTest
    const val androidTestAnnotationProcessor =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}" //androidTestAnnotationProcessor
}

object MapLibraries {
    const val maps = "com.google.android.gms:play-services-maps:17.0.0"
    const val location = "com.google.android.gms:play-services-location:17.0.0"
}

object ThirdPartyLibraries {
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
}

object GoogleMiscLibraries {
    const val playservices_auth =
        "com.google.android.gms:play-services-auth:19.0.0" //https://developers.google.com/android/guides/setup
    const val google_sheets =
        "com.google.apis:google-api-services-sheets:v4-rev612-1.25.0"//exclude: org.apache.httpcomponents //https://mvnrepository.com/artifact/com.google.apis/google-api-services-sheets/v4-rev612-1.25.0
    const val google_oauth_jetty =
        "com.google.oauth-client:google-oauth-client-jetty:1.33.1" ////https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client-jetty/1.33.1
    const val google_api_client =
        "com.google.api-client:google-api-client-android:1.33.2" //exclude: org.apache.httpcomponents https://mvnrepository.com/artifact/com.google.api-client/google-api-client-android/1.33.2
}
