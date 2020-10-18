plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    val coroutinesVersion: String by project
    val slf4jVersion: String by project

    api(project(":ok-favorites-be-common"))
    api(project(":ok-favorites-mp-transport-models"))

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
