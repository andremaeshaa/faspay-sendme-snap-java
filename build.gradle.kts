plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "id.co.faspay"
version = "1.0.0"


java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
    withSourcesJar()

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        // Remove vendor specification to use any available Java 11
    }

}

repositories {
    mavenCentral()
}

dependencies {
    // HTTP Client
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    implementation("org.bouncycastle:bcprov-jdk15to18:1.78")

    // JSON Processing
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")

    // Logging is now handled by internal implementation

    // Utilities
    implementation("org.apache.commons:commons-lang3:3.13.0")
    implementation("commons-codec:commons-codec:1.16.0")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.70")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.4.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.4.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "id.co.faspay.Main"
        )
    }
    // Disable the regular jar task as we'll use shadowJar instead
    enabled = false
}

tasks.shadowJar {
    archiveBaseName.set("faspay-sendme-snap-java")
    archiveClassifier.set("")
    archiveVersion.set("1.0.0")
    mergeServiceFiles()

    // Make shadowJar the default jar
    enabled = true
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            // Use shadow jar as the primary artifact
            artifact(tasks.shadowJar.get())

            // Set artifact details
            artifactId = "faspay-sendme-snap-java"
            groupId = "id.co.faspay"
            version = "1.0.0"
        }
    }

    repositories {
        maven {
            name = "local"
            url = uri("$buildDir/repo")
        }
    }
}
