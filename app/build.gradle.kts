plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.brunocvcunha.inutils4j:inutils4j:0.8")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
