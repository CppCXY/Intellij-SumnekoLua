import de.undercouch.gradle.tasks.download.Download
import org.gradle.internal.os.OperatingSystem

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0-Beta4"
    id("org.jetbrains.intellij") version "1.17.2"
    id("de.undercouch.download") version "5.3.0"
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
        ideaSDKShortVersion = "243",
        ideaSDKVersion = "2024.3",
        sinceBuild = "243",
        untilBuild = "251.*",
    )
)

group = "com.cppcxy"
val sumnekoVersion = "3.14.0"

val sumnekoProjectUrl = "https://github.com/LuaLS/lua-language-server"

val buildVersion = System.getProperty("IDEA_VER") ?: buildDataList.first().ideaSDKShortVersion

val buildVersionData = buildDataList.find { it.ideaSDKShortVersion == buildVersion }!!

val runnerNumber = System.getenv("RUNNER_NUMBER") ?: "Dev"

version = "${sumnekoVersion}.${runnerNumber}-IDEA${buildVersion}"

task("download", type = Download::class) {
    src(
        arrayOf(
            "${sumnekoProjectUrl}/releases/download/${sumnekoVersion}/lua-language-server-${sumnekoVersion}-win32-x64.zip",
            "${sumnekoProjectUrl}/releases/download/${sumnekoVersion}/lua-language-server-${sumnekoVersion}-linux-x64.tar.gz",
            "${sumnekoProjectUrl}/releases/download/${sumnekoVersion}/lua-language-server-${sumnekoVersion}-darwin-arm64.tar.gz",
            "${sumnekoProjectUrl}/releases/download/${sumnekoVersion}/lua-language-server-${sumnekoVersion}-darwin-x64.tar.gz",
        )
    )
    dest("temp")
}

task("makeServer", type = Copy::class) {
    dependsOn("download")
    from(zipTree("temp/lua-language-server-${sumnekoVersion}-win32-x64.zip")) {
        into("server/win32-x64")
    }
    from(tarTree("temp/lua-language-server-${sumnekoVersion}-linux-x64.tar.gz")) {
        into("server/linux-x64")
    }
    from(tarTree("temp/lua-language-server-${sumnekoVersion}-darwin-arm64.tar.gz")) {
        into("server/darwin-arm64")
    }
    from(tarTree("temp/lua-language-server-${sumnekoVersion}-darwin-x64.tar.gz")) {
        into("server/darwin-x64")
    }

    destinationDir = file("temp")
}


task("install", type = Copy::class) {
    dependsOn("makeServer")
    from("temp/server") {
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
    plugins.set(listOf("com.redhat.devtools.lsp4ij:0.7.0"))
}

repositories {
    maven(url = "https://www.jetbrains.com/intellij-repository/releases")
    maven(url = "https://plugins.jetbrains.com/plugins/nightly/23257")
    mavenCentral()
}

sourceSets {
    main {
        java.srcDirs("gen")
        resources.srcDir("resources")
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
    // fix by https://youtrack.jetbrains.com/issue/IDEA-325747/IDE-always-actively-disables-LSP-plugins-if-I-ask-the-plugin-to-return-localized-diagnostic-messages.
    runIde {
        autoReloadPlugins.set(false)
    }

    prepareSandbox {
        doLast {
            copy {
                from("${project.projectDir}/src/main/resources/server")
                into("${destinationDir.path}/${pluginName.get()}/server")
            }
        }
    }
}
