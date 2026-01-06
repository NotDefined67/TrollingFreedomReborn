plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta11"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
}

group = "me.leodev"
version = "3.1.0"
description = "A troll plugin with GUI - modified by leo"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://maven.citizensnpcs.co/repo")
    maven("https://repo.essentialsx.net/snapshots/")
}

dependencies {
    paperweight.paperDevBundle("1.21.11-R0.1-SNAPSHOT")
    compileOnly("net.dmulloy2:ProtocolLib:5.4.0") {
        exclude("*", "*")
    }
    implementation("commons-lang:commons-lang:2.6")
    implementation("com.github.cryptomorin:XSeries:13.6.0")
    compileOnly("net.citizensnpcs:citizens-main:2.0.41-SNAPSHOT") {
        exclude("*", "*")
    }
    compileOnly("net.essentialsx:EssentialsX:2.22.0-SNAPSHOT") {
        exclude("*", "*")
    }
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveFileName.set("TrollingFreedomReborn-${version}.jar")
        relocate("com.cryptomorin.xseries", "me.leodev.trollingfreedomreborn.other")
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
        // Adjust this path if the drive letter or folder structure differs
        into("C:/Projects/MineCraftTestServer/1.21.11/plugins")
    }

    build {
        finalizedBy(copyJar)
    }
}