// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1")
    }
}

plugins {
    id("com.android.application") version "7.2.2" apply false
    id ("com.android.library") version "7.2.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

task("clean") {
    delete(rootProject.buildDir)
}