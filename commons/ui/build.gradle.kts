import BuildAndroidConfig.compile_sdk_version
import BuildAndroidConfig.minimum_sdk_version
import BuildAndroidConfig.target_sdk_version
import extentions.addKotlinLibraries
import dependencies.BuildDependencies

plugins {
    id(BuildPlugins.android_library)
    kotlin(BuildPlugins.kotlin_android)
    kotlin(BuildPlugins.kotlin_kapt)
}

android {
    compileSdkVersion(compile_sdk_version)

    defaultConfig {
        minSdkVersion(minimum_sdk_version)
        targetSdkVersion(target_sdk_version)

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

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    addKotlinLibraries()
    BuildDependencies.run {
        implementation(fragment_ktx)
        implementation(recycler_view)
        implementation(app_compat)
        implementation(coil)
    }
}