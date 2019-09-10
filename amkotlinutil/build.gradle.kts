import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.LinkMapping
import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("maven-publish")
    id("com.android.library")
    id("com.github.dcendents.android-maven")
    kotlin("android")
    kotlin("android.extensions")
    id("org.jetbrains.dokka-android")
    id("maven")
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

    // buildTypes {
    //     named("release") {
    //         isMinifyEnabled = false
    //         proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    //     }
    //     named("stage") {
    //         isMinifyEnabled = false
    //         proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    //     }
    // }

    sourceSets {
        getByName("test").resources.srcDirs("test")
        getByName("main").assets.srcDirs("src/main/assets", "src/test/assets/")
    }

    androidExtensions {
        isExperimental = true
    }

}

dependencies {
    compileOnly(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    compileOnly("androidx.appcompat:appcompat:1.0.2")
    compileOnly("androidx.core:core-ktx:1.1.0")
    compileOnly("androidx.constraintlayout:constraintlayout:2.0.0-beta2")

    compileOnly("com.google.android.material:material:1.1.0-alpha10")
    compileOnly("org.greenrobot:eventbus:3.1.1")
    compileOnly("uk.co.chrisjenx:calligraphy:2.3.0")

    // --- GLIDE --- //
    compileOnly("com.github.bumptech.glide:glide:4.9.0")

    // --- CONDUCTOR --- //
    compileOnly("com.bluelinelabs", "conductor", version = conductorVersion)
    compileOnly("com.bluelinelabs", "conductor-support", version = conductorVersion)
    compileOnly("com.bluelinelabs", "conductor-rxlifecycle2", version = conductorVersion)

    // --- GROUPIE --- //
    compileOnly("com.xwray:groupie:2.5.1")
    compileOnly("com.xwray:groupie-kotlin-android-extensions:2.5.1")

    // --- RXJAVA2 --- //
    compileOnly("io.reactivex.rxjava2:rxjava:2.2.8")
    compileOnly("io.reactivex.rxjava2:rxandroid:2.1.1")
    compileOnly("io.reactivex.rxjava2:rxkotlin:2.3.0")

    // --- RETROFIT --- //
    compileOnly("com.squareup.retrofit2:retrofit:2.6.1")

    // --- WASABEEF --- //
    compileOnly("jp.wasabeef:blurry:3.0.0")
    compileOnly("jp.wasabeef:recyclerview-animators:3.0.0")
}

//add javadocs
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

        linkMapping(delegateClosureOf<LinkMapping> {
            dir = appPath.toPath().toString()
            url =
                "https://github.com/adaptmobile-organization/amkotlinutil/tree/master/$relativeAppPath"
            suffix = "#L"
        })

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
