import extentions.applyDefault

plugins.apply(BuildPlugins.git_hooks)
plugins.apply(BuildPlugins.update_dependencies)

allprojects {

    repositories.applyDefault()

    BuildPlugins.run {
        plugins.apply(spotless)
        plugins.apply(ktlint)
        plugins.apply(ben_manes)
    }

}