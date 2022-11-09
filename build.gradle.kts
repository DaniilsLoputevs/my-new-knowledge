import org.gradle.jvm.tasks.Jar


plugins {
    java
    kotlin("jvm") version "1.4.32"
}

group = "dl.knowledge"
version = "1.0-SNAPSHOT"
val kotlinMainClassPath = "main.MainKt"

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


/** dd - Deploy docker
 * Build Jar And Docker Image Then Deploy in to DockerHub (currently is only build jar for DockerImage)
 *
 * @see [https://stackoverflow.com/questions/41794914/how-to-create-a-fat-jar-with-gradle-kotlin-script/71092054]
 * */
task<Jar>("dd") {
    baseName = "${project.name}-docker"
    println("baseName     = $baseName")
    println("project.name = ${project.name}")
    manifest {
//        attributes["Implementation-Title"] = "Gradle Jar File Example"
        attributes["Implementation-Version"] = archiveVersion
        attributes["Main-Class"] = kotlinMainClassPath
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}