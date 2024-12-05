// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // TODO 1: hilt - import hilt plugin in project's build.gradle
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    // TODO 18: serialization - add the following plugins in project's build.gradle
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" apply false
}