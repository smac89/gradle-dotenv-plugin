/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Gradle plugin project to get you started.
 * For more details take a look at the Writing Custom Plugins chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.5/userguide/custom_plugins.html
 */
plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm").version("1.3.31")
    id("com.gradle.plugin-publish") version "0.10.1"
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

gradlePlugin {
    // Define the plugin
    val dotenv by plugins.creating {
        id = "com.github.otkmnb2783.dotenv"
        implementationClass = "com.github.otkmnb2783.dotenv.GradleDotenvPlugin"
    }
}

pluginBundle {
    // These settings are set for the whole plugin bundle
    website = "https://github.com/otkmnb2783/gradle-dotenv-plugin"
    vcsUrl = "https://github.com/otkmnb2783/gradle-dotenv-plugin"
    // tags and description can be set for the whole bundle here, but can also
    // be set / overridden in the config for specific plugins
    description = "This plugin loads the dotenv file so that it can be referenced from build.gradle."

    // The plugins block can contain multiple plugin entries.
    //
    // The name for each plugin block below (greetingsPlugin, goodbyePlugin)
    // does not affect the plugin configuration, but they need to be unique
    // for each plugin.

    // Plugin config blocks can set the id, displayName, version, description
    // and tags for each plugin.

    // id and displayName are mandatory.
    // If no version is set, the project version will be used.
    // If no tags or description are set, the tags or description from the
    // pluginBundle block will be used, but they must be set in one of the
    // two places.

    (plugins) {

        "dotenv" {
            // id is captured from java-gradle-plugin configuration
            displayName = "Gradle Dotenv plugin"
            tags = listOf("gradle", "tags", "per", "plugin")
            version = "0.0.1"
        }

    }

    // Optional overrides for Maven coordinates.
    // If you have an existing plugin deployed to Bintray and would like to keep
    // your existing group ID and artifact ID for continuity, you can specify
    // them here.
    //
    // As publishing to a custom group requires manual approval by the Gradle
    // team for security reasons, we recommend not overriding the group ID unless
    // you have an existing group ID that you wish to keep. If not overridden,
    // plugins will be published automatically without a manual approval process.
    //
    // You can also override the version of the deployed artifact here, though it
    // defaults to the project version, which would normally be sufficient.

    mavenCoordinates {
        groupId = "com.github.otkmnb2783"
        artifactId = "dotenv-plugins"
        version = "0.0.1"
    }
}

// Add a source set for the functional test suite
val functionalTestSourceSet = sourceSets.create("functionalTest") {
}

gradlePlugin.testSourceSets(functionalTestSourceSet)
configurations.getByName("functionalTestImplementation").extendsFrom(configurations.getByName("testImplementation"))

// Add a task to run the functional tests
val functionalTest by tasks.creating(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
}

val check by tasks.getting(Task::class) {
    // Run the functional tests as part of `check`
    dependsOn(functionalTest)
}
