
import BuildAndroidConfig.build_tools_version
import BuildAndroidConfig.compile_sdk_version
import BuildAndroidConfig.minimum_sdk_version
import BuildAndroidConfig.target_sdk_version
import BuildAndroidConfig.test_instrumentation_runner
import BuildAndroidConfig.version_code
import BuildAndroidConfig.version_name
import extentions.addKotlinLibraries
import extentions.addSharedLibraries

plugins {
    id(BuildPlugins.android_library)
    kotlin(BuildPlugins.kotlin_android)
    kotlin(BuildPlugins.kotlin_kapt)
    id(BuildPlugins.dagger_hilt_plugin)
}

android {
    compileSdkVersion(compile_sdk_version)
    buildToolsVersion(build_tools_version)

    defaultConfig {
        minSdkVersion(minimum_sdk_version)
        targetSdkVersion(target_sdk_version)
        versionCode = version_code
        versionName = version_name

        testInstrumentationRunner = test_instrumentation_runner
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}
dependencies {

    addKotlinLibraries()
    addSharedLibraries()
}
