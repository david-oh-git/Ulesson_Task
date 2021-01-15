package dependencies

object TestDependencies {

    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.mock_web_server}"
    const val androidx_test_core = "androidx.test:core:${Versions.androidx_test_core}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val androidx_junit_runner = "androidx.test:runner:${Versions.androidx_runner}"
    const val androidx_junit_rules = "androidx.test:rules:${Versions.androidx_runner}"
    const val junit = "junit:junit:${Versions.junit}"
    const val junit_vantage = "org.junit.vintage:junit-vintage-engine:${Versions.junit5}"
    const val junit5_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val junit5_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val junit5_platform = "org.junit.platform:junit-platform-launcher:${Versions.junit_platform}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val hamcrest = "org.hamcrest:hamcrest:${Versions.hamcrest}"
    const val arch_core = "androidx.arch.core:core-testing:${Versions.arch_core}"
    const val coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine_test}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    private object Versions {
        const val junit = "4.13.1"
        const val junit5 = "5.7.0"
        const val junit_platform = "1.7.0"
        const val androidx_junit = "1.1.2"
        const val espresso = "3.3.0"
        const val navigation_testing = "2.3.1"
        const val truth = "1.1"
        const val mockito = "2.19.0"
        const val hamcrest = "2.2"
        const val arch_core = "2.1.0"
        const val coroutine_test = "1.4.1"
        const val mockito_kotlin = "2.2.0"
        const val mockk = "1.10.2"
        const val androidx_runner = "1.3.0"
        const val robolectric = "4.4"
        const val androidx_test_core = "1.3.0"
        const val mock_web_server = "4.9.0"
    }
}