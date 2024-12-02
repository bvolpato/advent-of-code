plugins {
    application
    // Spotless
    id("com.diffplug.spotless") version "7.0.0.BETA4"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.brunocvcunha.inutils4j:inutils4j:0.8")
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("junit:junit:4.13.2")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// Configure Spotless plugin to use googleJavaFormat 1.21.0
spotless {
    java {
        googleJavaFormat("1.21.0")
    }
}

