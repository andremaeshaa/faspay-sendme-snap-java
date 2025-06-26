plugins {
    id("java")
    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "id.co.faspay"
version = "1.0.0"

// Configure Nexus publishing
nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(findProperty("sonatype.username") as String? ?: "")
            password.set(findProperty("sonatype.password") as String? ?: "")
        }
    }
}

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
}

tasks.shadowJar {
    archiveBaseName.set("faspay-sendme-snap-java")
    archiveClassifier.set("")
    archiveVersion.set("1.0.0")
    mergeServiceFiles()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
//            project.shadow.component(this)
            from(components["java"])

            // Pastikan artifactId dan groupId benar
            artifactId = "faspay-sendme-snap-java"
            groupId = "id.co.faspay"
            version = "1.0.0"

            artifact(tasks.shadowJar.get()) {
                classifier = "all" // ini hanya tambahan, bukan artefak utama
            }

            pom {
                name.set("Faspay SendMe Snap Java SDK")
                description.set("Java SDK for Faspay SendMe Snap API")
                url.set("https://github.com/faspay/faspay-sendme-snap-java")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("faspay")
                        name.set("Faspay")
                        email.set("andremaesha@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/andremaeshaa/faspay-sendme-snap-java.git")
                    developerConnection.set("scm:git:ssh://github.com/andremaeshaa/faspay-sendme-snap-java.git")
                    url.set("https://github.com/andremaeshaa/faspay-sendme-snap-java")
                }
            }
        }
    }

    repositories {
        maven {
            name = "local"
            url = uri("$buildDir/repo")
        }
    }
}

// Configure signing
signing {
    val signingKeyId: String? = findProperty("signing.keyId") as String?
    val signingPassword: String? = findProperty("signing.password") as String?
    val signingSecretKeyRingFile: String? = findProperty("signing.secretKeyRingFile") as String?

    if (signingSecretKeyRingFile != null && file(signingSecretKeyRingFile).exists()) {
        useInMemoryPgpKeys(signingKeyId, signingPassword, file(signingSecretKeyRingFile).readText())
    } else {
        // Use gpg command line tool
        useGpgCmd()
    }

    sign(publishing.publications["maven"])
}
