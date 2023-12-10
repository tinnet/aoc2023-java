plugins {
    id("java")
    application
}

group = "net.coronam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:32.1.3-jre")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("one.util:streamex:0.8.2")
    
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.assertj:assertj-guava:3.24.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("net.coronam.Main")
}

tasks.test {
    useJUnitPlatform()
}