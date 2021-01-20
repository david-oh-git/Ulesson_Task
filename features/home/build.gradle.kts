import BuildAndroidConfig.compile_sdk_version
import BuildAndroidConfig.minimum_sdk_version
import BuildAndroidConfig.target_sdk_version
import BuildAndroidConfig.test_instrumentation_runner
import dependencies.BuildDependencies
import extentions.addKotlinLibraries
import extentions.addNavigationLibraries
import extentions.addSharedLibraries

plugins {
    id("com.android.dynamic-feature")
//    id(BuildPlugins.android_library)
    kotlin(BuildPlugins.kotlin_android)
    kotlin(BuildPlugins.kotlin_kapt)
    id("androidx.navigation.safeargs.kotlin")
}
android {
    compileSdkVersion(compile_sdk_version)

    defaultConfig {
        minSdkVersion(minimum_sdk_version)
        targetSdkVersion(target_sdk_version)

        testInstrumentationRunner = test_instrumentation_runner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = false
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    buildFeatures.viewBinding = true

}

dependencies {
    implementation(project(Modules.app))
    implementation( project(Modules.ui))
    implementation( project(Modules.core) )

    addKotlinLibraries()
    addSharedLibraries()
    addNavigationLibraries()
    BuildDependencies.run {
        implementation(fragment_ktx)
        implementation(app_compat)
        implementation(constraints_layout)
        implementation(material_components)
        implementation(swipe_refresh_layout)
        implementation(recycler_view)
        implementation(livedata_ktx)
    }
}