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

}


application {
        mainClass.set("net.octocore.Main")
}

