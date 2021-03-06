@file:Suppress("SpellCheckingInspection")
package dependencies

/**
 * All dependencies used in this project.
 */
object BuildDependencies {

    const val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_refresh_layout}"
    const val play_core = "com.google.android.play:core:${Versions.play_core}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    const val dagger_hilt_kapt = "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt}"
    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.dagger_hilt}"
    const val recycler_view = "androidx.recyclerview:recyclerview:${Versions.recycler_view}"
    const val preference = "androidx.preference:preference-ktx:${Versions.preference}"
    const val material_spinner = "com.jaredrummler:material-spinner:${Versions.material_spinner}"
    const val dynamic_features_nav = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigation_dyn_feat = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val room_db = "androidx.room:room-runtime:${Versions.room_db}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_db}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room_db}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val http_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.http_logging}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
    const val kotlin_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    const val constraints_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraints_layout}"
    const val material_components = "com.google.android.material:material:${Versions.material_components}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val exo_player_core = "com.google.android.exoplayer:exoplayer-core:${Versions.exo_player}"
    const val exo_player_dash = "com.google.android.exoplayer:exoplayer-dash:${Versions.exo_player}"
    const val exo_player_ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.exo_player}"

    private object Versions {
        const val kotlin = "1.4.21"
        const val app_compat = "1.2.0"
        const val constraints_layout = "2.0.4"
        const val material_components = "1.2.1"
        const val kotlin_coroutines = "1.4.2"
        const val retrofit = "2.9.0"
        const val http_logging = "4.9.0"
        const val timber = "4.7.1"
        const val navigation = "2.3.2"
        const val room_db = "2.2.5"
        const val lifecycle = "2.2.0"
        const val material_spinner = "1.3.1"
        const val preference = "1.1.1"
        const val recycler_view = "1.1.0"
        const val dagger_hilt = "2.28-alpha"
        const val fragment_ktx = "1.2.5"
        const val play_core = "1.8.0"
        const val swipe_refresh_layout = "1.1.0"
        const val coil = "1.0.0"
        const val exo_player = "2.12.1"
    }
}