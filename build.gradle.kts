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
        implementation("tools.jackson.core:jackson-core:3.2.0")
        implementation("tools.jackson.core:jackson-databind:3.2.0")
}


application {
        mainClass.set(System.getProperty("exec.mainClass") ?: "net.octocore.Main")
}

