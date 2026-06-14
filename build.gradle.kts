plugins {
    id("java")
    kotlin("jvm") version "2.4.0"
    id("com.gradleup.shadow") version "9.4.2"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
}

paperweight {
    reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

group = "com.leomadrassi"
version = "3.3.0"
description = "A troll plugin with GUI - updated and maintained by leo"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://maven.citizensnpcs.co/repo")
    maven("https://repo.essentialsx.net/snapshots/")
}

dependencies {
    kotlin("stdlib")
    paperweight.paperDevBundle("26.1.2.build.+")
    compileOnly("net.dmulloy2:ProtocolLib:5.4.0") {
        exclude("*", "*")
    }
    implementation("commons-lang:commons-lang:2.6")
    implementation("com.github.cryptomorin:XSeries:13.7.0")
    compileOnly("net.citizensnpcs:citizens-main:2.0.42-SNAPSHOT") {
        exclude("*", "*")
    }
    compileOnly("net.essentialsx:EssentialsX:2.22.1-SNAPSHOT") {
        exclude("*", "*")
    }
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

kotlin {
    jvmToolchain(25)
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/java")
        }
    }
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveFileName.set("TrollingFreedomReborn-${version}.jar")
        relocate("com.cryptomorin.xseries", "com.leomadrassi.trollingfreedomreborn.other")
        minimize()
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
        val props = mapOf(
            "name" to project.name,
            "version" to project.version,
            "description" to project.description,
            "apiVersion" to "1.13"
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
    val copyJar = register<Copy>("copyJar") {
        from(shadowJar.get().archiveFile)
        into("C:/Leo/Projects/PluginsMinecraft/TrollingFreedomReborn/jar")
    }

    build {
        finalizedBy(copyJar)
    }
}
