# amkotlinutil
Kotlin extensions and utility

# [LINK to DOCUMENTATION](https://amkotlinutil-docs.firebaseapp.com/)

## Installing

#### Add jitpack repository to top (project) build.gradle file:
```groovy
maven { url 'https://jitpack.io' }

Example:
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

#### Add dependency to app (module) build.gradle file:

Example:
```groovy
dependencies {
        implementation 'com.github.adaptmobile-organization:amkotlinutil:<current_version>'
}
```
    

## Development

Push a new/the next tag for a new version of amkotlinutil on jitpack

```bash
git tag -a 1.0.0 -m "Release 1.0.0"
git push --tags
```

## Jitpack

https://jitpack.io/#adaptmobile-organization/amkotlinutil
