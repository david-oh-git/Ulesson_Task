/*
 * MIT License
 *
 * Copyright (c) 17/11/2020 16:23   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package extentions

import dependencies.BuildDependencies.dagger_hilt
import dependencies.BuildDependencies.dagger_hilt_kapt
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.TestDependencies
import dependencies.BuildDependencies.kotlin
import dependencies.BuildDependencies.kotlin_coroutines_android
import dependencies.BuildDependencies.kotlin_coroutines_core
import dependencies.BuildDependencies.timber
import dependencies.BuildDependencies.kotlin_reflect
import dependencies.BuildDependencies.room_db
import dependencies.BuildDependencies.room_ktx
import dependencies.BuildDependencies.room_compiler

/**
 * Add debugImplementation configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? =
        add("debugImplementation", dependencyNotation)

/**
 * Add implementation configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
        add("implementation", dependencyNotation)

/**
 * Add api configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.api(dependencyNotation: String): Dependency? =
        add("api", dependencyNotation)

/**
 * Add kapt configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
        add("kapt", dependencyNotation)

/**
 * Add testImplementation configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
        add("testImplementation", dependencyNotation)

/**
 * Add androidTestImplementation configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
        add("androidTestImplementation", dependencyNotation)

/**
 * Add testRuntimeOnly configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.testRuntimeOnly(dependencyNotation: String): Dependency? =
        add("testRuntimeOnly", dependencyNotation)

/**
 * Add androidTestRuntimeOnly configuration.
 * @param dependencyNotation name of dependency to add.
 * @return The dependency.
 */
fun DependencyHandler.androidTestRuntimeOnly(dependencyNotation: String): Dependency? =
        add("androidTestRuntimeOnly", dependencyNotation)

/**
 * Adds all test dependencies
 */
fun DependencyHandler.addIntegratedTestsDependencies(){

    androidTestImplementation(TestDependencies.junit5_api)
    androidTestImplementation(TestDependencies.junit5_platform)
    androidTestImplementation(TestDependencies.truth)
    androidTestImplementation(TestDependencies.mockk)
    androidTestImplementation(TestDependencies.androidx_junit_runner)
    androidTestImplementation(TestDependencies.androidx_junit_rules)

    androidTestRuntimeOnly(TestDependencies.junit5_engine)
}

fun DependencyHandler.addUnitTestsDependencies() {

    testImplementation(TestDependencies.junit5_api)
    testImplementation(TestDependencies.junit5_platform)
    testImplementation(TestDependencies.truth)
    testImplementation(TestDependencies.mockito)
    testImplementation(TestDependencies.hamcrest)
    testImplementation(TestDependencies.coroutine_test)
    testImplementation(TestDependencies.arch_core)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.robolectric)
    testImplementation(TestDependencies.androidx_test_core)
    testImplementation(TestDependencies.androidx_junit_runner)
    testImplementation(TestDependencies.mock_web_server)

    testRuntimeOnly(TestDependencies.junit5_engine)
}

fun DependencyHandler.addSharedLibraries() {
    implementation(timber)
    implementation(dagger_hilt)

    kapt(dagger_hilt_kapt)
}

fun DependencyHandler.addKotlinLibraries() {
    implementation(kotlin)
    implementation(kotlin_reflect)
    implementation(kotlin_coroutines_android)
    implementation(kotlin_coroutines_core)
}

fun DependencyHandler.addRoomDbDependencies() {
    implementation(room_db)
    implementation(room_ktx)

    kapt(room_compiler)
}