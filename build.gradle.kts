plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.24"
}

group = "ru.kekih"
version = "1.0.0"
description = "Execute commands as console via /console"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("26.3.build.+")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(25)
    }
    processResources {
        val props = mapOf(
            "version" to version,
            "description" to project.description
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
    jar {
        archiveBaseName.set("ConsoleCommand")
    }
    assemble {
        // For pure API plugins reobf is optional, but keep it simple
    }
}
