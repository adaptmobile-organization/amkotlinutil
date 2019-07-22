# amkotlinutil
Kotlin extensions and utility

# [LINK to DOCUMENTATION](https://amkotlinutil-docs.firebaseapp.com/)

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
        compile 'com.github.adaptmobile-organization:amkotlinutil:1.0.1'
    }

## Development

  Push a new/the next tag for a new version of amkotlinutil on jitpack
  
  ```
  git tag -a 1.0.0 -m "Release 1.0.0"
  git push --tags
  ```

## Jitpack

  https://jitpack.io/#adaptmobile-organization/amutil
