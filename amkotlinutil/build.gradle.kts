import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.LinkMapping
import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    id("maven-publish")
    id("com.github.dcendents.android-maven")
    kotlin("android")
    kotlin("android.extensions")
    id("org.jetbrains.dokka-android")
    id("maven")
    id("org.jlleitschuh.gradle.ktlint")
}

val conductorVersion = "2.1.5"

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 2
        versionName = "6.6.10"
    }

    sourceSets {
        getByName("test").resources.srcDirs("test")
        getByName("main").assets.srcDirs("src/main/assets", "src/test/assets/")
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

ktlint {
    android.set(true)
    ignoreFailures.set(true)
    verbose.set(true)
    enableExperimentalRules.set(true)
}

dependencies {

    compileOnly(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    compileOnly(Libs.appcompat)
    compileOnly(Libs.core_ktx)
    compileOnly(Libs.constraintlayout)

    compileOnly(Libs.material)
    compileOnly(Libs.eventbus)
    compileOnly(Libs.calligraphy)

    // --- GLIDE --- //
    compileOnly(Libs.glide)

    // --- CONDUCTOR --- //
    compileOnly(Libs.conductor)
    compileOnly(Libs.conductor_support)
    compileOnly(Libs.conductor_rxlifecycle2)

    // --- GROUPIE --- //
    compileOnly(Libs.groupie)
    compileOnly(Libs.groupie_kotlin_android_extensions)

    // --- RXJAVA2 --- //
    compileOnly(Libs.rxjava)
    compileOnly(Libs.rxandroid)
    compileOnly(Libs.rxkotlin)

    // --- RETROFIT --- //
    compileOnly(Libs.retrofit)

    // --- WASABEEF --- //
    compileOnly(Libs.blurry)
    compileOnly(Libs.recyclerview_animators)
}

// add javadocs
tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from("android.sourceSets.main.java.srcDirs")
    }

    val dokka by existing(DokkaTask::class) {
        outputFormat = "markdown"
        outputDirectory = "docs"
        includeNonPublic = false
        skipDeprecated = false
        reportUndocumented = false
        skipEmptyPackages = true

        val appPath = File(project.projectDir, "/src/main/java")
        val relativeAppPath = rootDir.toPath().relativize(appPath.toPath()).toString()

        linkMapping(
            delegateClosureOf<LinkMapping> {
                dir = appPath.toPath().toString()
                url =
                    "https://github.com/adaptmobile-organization/amkotlinutil/tree/master/$relativeAppPath"
                suffix = "#L"
            }
        )
    }

    val javadocJar by creating(Jar::class) {
        dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
        archiveClassifier.set("javadoc")
        from(dokka)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
    }
}

group = "com.github.adaptmobile"
