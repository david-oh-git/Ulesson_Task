import BuildAndroidConfig.application_id
import BuildAndroidConfig.build_tools_version
import BuildAndroidConfig.compile_sdk_version
import BuildAndroidConfig.minimum_sdk_version
import BuildAndroidConfig.target_sdk_version
import BuildAndroidConfig.test_instrumentation_runner
import BuildAndroidConfig.version_code
import BuildAndroidConfig.version_name
import dependencies.BuildDependencies
import extentions.addKotlinLibraries
import extentions.addSharedLibraries
import extentions.addNavigationLibraries
import extentions.getLocalProperty
import Modules.core

plugins {
    id(BuildPlugins.android_application)
    kotlin(BuildPlugins.kotlin_android)
    kotlin(BuildPlugins.kotlin_kapt)
    id(BuildPlugins.dagger_hilt_plugin)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(compile_sdk_version)
    buildToolsVersion(build_tools_version)

    defaultConfig {
        applicationId = application_id
        minSdkVersion(minimum_sdk_version)
        targetSdkVersion(target_sdk_version)
        versionCode = version_code
        versionName = version_name

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

    signingConfigs {

        getByName(BuildType.debug) {
            keyAlias = "android"
            keyPassword = "android"
            storeFile = file("ulesson_debug.jks")
            storePassword = "android"

            isV2SigningEnabled = true
        }

//        create(BuildType.release){
//            keyAlias = getLocalProperty("signing.key.alias")
//            keyPassword = getLocalProperty("signing.key.password")
//            storeFile = file(getLocalProperty("signing.store.file"))
//            storePassword = getLocalProperty("signing.store.password")
//
//            isV2SigningEnabled = true
//        }
    }

    buildTypes {

        getByName(BuildType.release){
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled

//            signingConfig =  signingConfigs.getByName(BuildType.release)
            proguardFiles( getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }

        getByName(BuildType.debug){
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled

            signingConfig = signingConfigs.getByName(BuildType.debug)

        }

    }

    buildFeatures {
        viewBinding = true
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    dynamicFeatures = mutableSetOf(
        Modules.home, Modules.chapter_list
    )
}

dependencies {
    implementation( fileTree( mapOf( "dir" to "libs", "include" to  listOf("*.jar")  )))

//    implementation( project(Modules.home))
    implementation( project(core))
    api( project(Modules.ui))

    addKotlinLibraries()
    addSharedLibraries()
    addNavigationLibraries()
    BuildDependencies.run {
        implementation(play_core)
        implementation(app_compat)
        implementation(material_components)
        implementation(constraints_layout)
        api(livedata_ktx)
    }
}