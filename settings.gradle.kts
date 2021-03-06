rootProject.name = "otuskotlin-202007-favorites"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val bmuschkoVersion: String by settings


        kotlin("multiplatform") version kotlinVersion apply false
        kotlin("jvm") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
        id("com.bmuschko.docker-java-application") version bmuschkoVersion apply false
    }
}
include("ok-favorites-be-common")
include("ok-favorites-mp-common")
include("ok-favorites-mp-transport-models")
include("ok-favorites-be-transport-mp")
include("ok-favorites-be-app-jetty")
include("ok-favorites-be-app-ktor")
include("ok-favorites-be-logic")
