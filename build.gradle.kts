// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
        classpath(kotlin("gradle-plugin", version = "1.3.50"))

        classpath("com.github.dcendents:android-maven-gradle-plugin:2.0")
        classpath("org.jetbrains.dokka", "dokka-android-gradle-plugin", version = "0.9.18")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version "0.4.2"
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { setUrl("https://maven.google.com") }
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildSrcVersions {
    useFdqnFor = mutableListOf()
    renameLibs = "Libs"
    renameVersions = "Versions"
    indent = "  "
    rejectedVersionKeywords("alpha", "beta", "rc", "cr", "m", "preview", "eap")
}