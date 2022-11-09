//import org.jetbrains.kotlin.contracts.model.structure.UNKNOWN_COMPUTATION.type
//import sun.tools.jar.resources.jar
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.cli.jvm.compiler.findMainClass


plugins {
    java
    kotlin("jvm") version "1.4.32"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val kotlinMainClassPath = "main.Main"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}



// https://stackoverflow.com/questions/41794914/how-to-create-a-fat-jar-with-gradle-kotlin-script/71092054
val fatJar = task<Jar>("fatJar") {
    baseName = "${project.name}-fat"
    println("baseName     = $baseName")
    println("project.name = ${project.name}")
    manifest {
        attributes["Implementation-Title"] = "Gradle Jar File Example"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = kotlinMainClassPath
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}

//tasks.jar {
//    manifest.attributes["Main-Class"] = kotlinMainClassPath
//    val dependencies = configurations
//        .runtimeClasspath
//        .get()
//        .map(::zipTree) // OR .map { zipTree(it) }
//    from(dependencies)
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//}