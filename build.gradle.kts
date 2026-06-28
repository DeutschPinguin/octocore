plugins {
    id("java")
    id("application")
}


group = "net.octocore"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}


dependencies {
        implementation(platform("org.apache.logging.log4j:log4j-bom:2.26.0"))
        implementation("org.apache.logging.log4j:log4j-api")
        implementation("org.apache.logging.log4j:log4j-core")

        implementation("tools.jackson.core:jackson-core:3.2.0")
        implementation("tools.jackson.core:jackson-databind:3.2.0")
}


application {
    mainClass.set(System.getProperty("exec.mainClass") ?: "net.octocore.Main")
}