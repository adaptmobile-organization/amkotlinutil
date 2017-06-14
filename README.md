#AMUtil

## Installing

#### Add jitpack repository to top (project) build.gradle file:

    maven { url 'https://jitpack.io' }

    Example:
    allprojects {
        repositories {
            jcenter()
            maven { url 'https://jitpack.io' }
        }
    }

#### Add dependency to app (module) build.gradle file:

    compile 'com.github.adaptmobile-organization:amutil:version'

  Example:
    
    dependencies {
        compile 'com.github.adaptmobile-organization:amutil:1.2.0'
    }

## Development

  Push a new/the next tag for a new version of amutil on jitpack

## Jitpack

  https://jitpack.io/#adaptmobile-organization/amutil
