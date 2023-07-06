import de.undercouch.gradle.tasks.download.Download
import org.gradle.internal.os.OperatingSystem

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    id("org.jetbrains.intellij") version "1.13.3"
    id("de.undercouch.download").version("5.3.0")
}

data class BuildData(
        val ideaSDKShortVersion: String,
        // https://www.jetbrains.com/intellij-repository/releases
        val ideaSDKVersion: String,
        val sinceBuild: String,
        val untilBuild: String,
        val archiveName: String = "IntelliJ-SumnekoLua",
        val jvmTarget: String = "17",
        val targetCompatibilityLevel: JavaVersion = JavaVersion.VERSION_17,
        // https://github.com/JetBrains/gradle-intellij-plugin/issues/403#issuecomment-542890849
        val instrumentCodeCompilerVersion: String = ideaSDKVersion,
        val type: String = "IC"
)

val buildDataList = listOf(
        BuildData(
                ideaSDKShortVersion = "232",
                ideaSDKVersion = "LATEST-EAP-SNAPSHOT",
                sinceBuild = "232",
                untilBuild = "232.*",
                type = "IU"
        )
)

group = "com.cppcxy"
val sumnekoVersion = "3.6.22"

val sumnekoProjectUrl = "https://github.com/LuaLS/lua-language-server"

val buildVersion = System.getProperty("IDEA_VER") ?: buildDataList.first().ideaSDKShortVersion

val buildVersionData = buildDataList.find { it.ideaSDKShortVersion == buildVersion }!!

val runnerNumber = System.getenv("RUNNER_NUMBER") ?: "Dev"

version = "${sumnekoVersion}.${runnerNumber}-IDEA${buildVersion}"

val os = OperatingSystem.current()
val sumnekoZip = if (os.isWindows) {
    "lua-language-server-${sumnekoVersion}-win32-x64.zip"
} else if (os.isLinux) {
    "lua-language-server-${sumnekoVersion}-linux-x64.tar.gz"
} else {
    "lua-language-server-${sumnekoVersion}-darwin-x64.tar.gz"
}

// for future it should be lua-language-server-intellij
task("download", type = Download::class) {
    src(arrayOf(
            "${sumnekoProjectUrl}/releases/download/${sumnekoVersion}/${sumnekoZip}",
    ))
    dest(sumnekoZip)
}


task("install", type = Copy::class) {
    dependsOn("download")
    from(zipTree("${sumnekoZip}")) {
        into("server")
    }
    destinationDir = file("src/main/resources")
}


// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName.set("Sumneko-Lua")
    version.set(buildVersionData.ideaSDKVersion)
    type.set(buildVersionData.type) // Target IDE Platform
    sandboxDir.set("${project.buildDir}/${buildVersionData.ideaSDKShortVersion}/idea-sandbox")
    plugins.set(listOf(/* Plugin Dependencies */))
}

repositories {
    maven(url = "https://www.jetbrains.com/intellij-repository/releases")
    mavenCentral()
}

sourceSets {
    main {
        java.srcDirs("gen")
        resources.srcDir("src/main/resources")
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = buildVersionData.jvmTarget
    }

    patchPluginXml {
        sinceBuild.set(buildVersionData.sinceBuild)
        untilBuild.set(buildVersionData.untilBuild)
    }

    instrumentCode {
        compilerVersion.set(buildVersionData.instrumentCodeCompilerVersion)
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    buildPlugin {
        dependsOn("install")
    }
}
