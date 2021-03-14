buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("com.android.tools.build:gradle:4.0.2")
    }
}

plugins {
    kotlin("multiplatform") version "1.4.31"
    id("com.android.library")
}

group = "net.alphatab"
version = "1.3-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    jcenter()
    maven("https://packages.jetbrains.team/maven/p/skija/maven")
}

kotlin {
    android()
//    iosX64("ios") {
//        binaries {
//            framework {
//                baseName = "library"
//            }
//        }
//    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "10"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
            }
            kotlin.srcDirs("../../dist/lib.kotlin/src")
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core:1.3.2")
                implementation("androidx.core:core-ktx:1.3.2")
            }
            kotlin.srcDirs("src/jvmCommon/kotlin")
        }

        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
            kotlin.srcDirs("../../dist/lib.kotlin/test")
        }

//        val iosMain by getting
//        val iosTest by getting

        val os = System.getProperty("os.name")
        val target = when {
            os == "Mac OS X" -> {
                "macos-x64"
            }
            os.startsWith("Win") -> {
                "windows"
            }
            os.startsWith("Linux") -> {
                "linux"
            }
            else -> {
                throw Error("Unsupported OS: $os")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.skija:skija-$target:0.89.32")
            }
            kotlin.srcDirs("src/jvmCommon/kotlin")
            resources.srcDirs("../../font/").apply {
                this.filter.include("**/*.ttf")
                this.filter.include("**/*.sf2")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
            kotlin.srcDirs("../../dist/lib.kotlin/test")
        }
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }
}
