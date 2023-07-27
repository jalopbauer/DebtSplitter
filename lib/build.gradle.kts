plugins {
    kotlin("jvm") version "1.9.0"
    `java-library`
}
group = "indigo"
version = "0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
