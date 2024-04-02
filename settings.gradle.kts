pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EzloTest"
include(":app")
include(":core:navigation")
include(":feature:device")
include(":core:domain")
include(":core:data")
include(":core:ui")
