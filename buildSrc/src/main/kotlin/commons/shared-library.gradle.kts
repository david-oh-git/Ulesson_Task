
import dependencies.BuildDependencies
import extentions.implementation

plugins {
    id("kotlin-android")
}

dependencies {

    BuildDependencies.run {
        implementation(kotlin)
        implementation(kotlin_coroutines_android)
        implementation(kotlin_coroutines_core)
        implementation(timber)
    }
}