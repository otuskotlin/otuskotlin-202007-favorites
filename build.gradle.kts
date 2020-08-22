group = "ru.otus.otuskotlin.favorites"
version = "0.0.1"

plugins {
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}
