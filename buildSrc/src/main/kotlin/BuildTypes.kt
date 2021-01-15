
interface BuildType {

    companion object {
        const val debug = "debug"
        const val release = "release"
    }

    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeDebug : BuildType {

    override val isMinifyEnabled: Boolean
        get() = false

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-debug"
    override val isTestCoverageEnabled = true
}

object BuildTypeRelease : BuildType {

    override val isMinifyEnabled: Boolean
        get() = true
    override val isTestCoverageEnabled: Boolean
        get() = false
}