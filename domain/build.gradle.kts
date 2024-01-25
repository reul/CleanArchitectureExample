import org.jetbrains.kotlin.gradle.dsl.JvmTarget

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `java-library`
    alias(libs.plugins.org.jetbrains.kotlin.jvm)

}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

kotlin {
    compilerOptions {
        this.jvmTarget.set(JvmTarget.JVM_19)
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}