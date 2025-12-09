import de.undercouch.gradle.tasks.download.Download

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.20-Beta2"
    id("org.jetbrains.intellij.platform") version "2.6.0"
    id("de.undercouch.download") version "5.6.0"
}

// ============= 项目配置 =============
group = "com.cppcxy"

// 版本配置
object Versions {
    const val luals = "3.16.1"
    const val jvm = "17"
    const val ideaSDK = "2025.2"
}

// 构建数据配置
data class BuildData(
    val ideaSDKShortVersion: String,
    val ideaSDKVersion: String,
    val sinceBuild: String,
    val untilBuild: String,
    val type: String = "IC"
)

private val buildDataList = listOf(
    BuildData(
        ideaSDKShortVersion = "252",
        ideaSDKVersion = "2025.2",
        sinceBuild = "252",
        untilBuild = "253.*"
    )
)

// 动态版本配置
private val buildVersion = System.getProperty("IDEA_VER") ?: buildDataList.first().ideaSDKShortVersion
private val buildVersionData = buildDataList.find { it.ideaSDKShortVersion == buildVersion }
    ?: error("Unsupported IDEA version: $buildVersion")
private val runnerNumber = System.getenv("RUNNER_NUMBER") ?: "Dev"

version = "${Versions.luals}.${runnerNumber}-IDEA${buildVersion}"

// 下载URL配置
object DownloadUrls {
    private const val lualsProjectUrl = "https://github.com/LuaLS/lua-language-server"

    // GitHub 镜像配置
    private val githubMirror = System.getProperty("github.mirror") ?: ""
    private fun applyMirror(url: String): String {
        return if (githubMirror.isNotEmpty() && url.startsWith("https://github.com/")) {
            url.replace("https://github.com/", githubMirror)
        } else url
    }

    val luals = arrayOf(
        "$lualsProjectUrl/releases/download/${Versions.luals}/lua-language-server-${Versions.luals}-win32-x64.zip",
        "$lualsProjectUrl/releases/download/${Versions.luals}/lua-language-server-${Versions.luals}-linux-x64.tar.gz",
        "$lualsProjectUrl/releases/download/${Versions.luals}/lua-language-server-${Versions.luals}-darwin-arm64.tar.gz",
        "$lualsProjectUrl/releases/download/${Versions.luals}/lua-language-server-${Versions.luals}-darwin-x64.tar.gz"
    ).map { applyMirror(it) }.toTypedArray()
}

// ============= 任务配置 =============

val downloadLuals by tasks.registering(Download::class) {
    group = "build setup"
    description = "Download Luals for all platforms"

    src(DownloadUrls.luals)
    dest("temp/luals")
    overwrite(false)
}


// 解压所有下载的文件
val extractDependencies by tasks.registering(Copy::class) {
    group = "build setup"
    description = "Extract downloaded luals dependencies"

    dependsOn(downloadLuals)
    duplicatesStrategy = DuplicatesStrategy.WARN

    // 解压语言服务器
    from(zipTree("temp/luals/lua-language-server-${Versions.luals}-win32-x64.zip")) {
        into("server/win32-x64")
    }
    from(tarTree("temp/luals/lua-language-server-${Versions.luals}-linux-x64.tar.gz")) {
        into("server/linux-x64")
    }
    from(tarTree("temp/luals/lua-language-server-${Versions.luals}-darwin-arm64.tar.gz")) {
        into("server/darwin-arm64")
    }
    from(tarTree("temp/luals/lua-language-server-${Versions.luals}-darwin-x64.tar.gz")) {
        into("server/darwin-x64")
    }

    destinationDir = file("temp/extracted")
}

// 安装依赖到资源目录
val installDependencies by tasks.registering(Copy::class) {
    group = "build setup"
    description = "Install luals dependencies to resources directory"

    dependsOn(extractDependencies)
    duplicatesStrategy = DuplicatesStrategy.WARN

    // 复制语言服务器
    from("temp/extracted/server") {
        into("server")
    }

    destinationDir = file("src/main/resources")
}

// 清理任务
val cleanDependencies by tasks.registering(Delete::class) {
    group = "build setup"
    description = "Clean downloaded and extracted dependencies"

    delete("temp")
}

// ============= 仓库配置 =============
repositories {
    // 添加阿里云镜像仓库优先使用
    maven {
        url = uri("https://maven.aliyun.com/repository/central")
        name = "AliyunMavenCentral"
    }
    maven {
        url = uri("https://maven.aliyun.com/repository/gradle-plugin")
        name = "AliyunGradlePlugin"
    }
    maven {
        url = uri("https://maven.aliyun.com/repository/google")
        name = "AliyunGoogle"
    }

    // 保留原有仓库作为备用
    mavenCentral()
    google()
    gradlePluginPortal()

    intellijPlatform {
        defaultRepositories()
    }
}

// ============= 依赖配置 =============
dependencies {
    intellijPlatform {
        intellijIdeaCommunity(Versions.ideaSDK)
        bundledPlugins("com.intellij.java", "org.jetbrains.kotlin")
        plugins("com.redhat.devtools.lsp4ij:0.18.0")
    }
}

// ============= 源码集配置 =============
sourceSets {
    main {
        java.srcDirs("gen")
        resources.srcDirs("resources")
        resources.exclude("server/**/*")
    }
}

// ============= IntelliJ 平台配置 =============
intellijPlatform {
    buildSearchableOptions = false
    projectName = "IntelliJ-SumnekoLua"

    pluginConfiguration {
        name = "SumnekoLua"
    }

    publishing {
        token = System.getenv("PUBLISH_TOKEN")
    }

    signing {
        certificateChain = System.getenv("CERTIFICATE_CHAIN")
        privateKey = System.getenv("PRIVATE_KEY")
        password = System.getenv("PRIVATE_KEY_PASSWORD")
    }
}

// ============= 任务配置 =============
tasks {
    // Java 编译配置
    withType<JavaCompile> {
        sourceCompatibility = Versions.jvm
        targetCompatibility = Versions.jvm
        options.encoding = "UTF-8"
    }

    // Kotlin 编译配置
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(Versions.jvm))
        }
    }

    // 插件XML配置
    patchPluginXml {
        dependsOn(installDependencies)
        sinceBuild.set(buildVersionData.sinceBuild)
        untilBuild.set(buildVersionData.untilBuild)
    }

    // 构建插件
    buildPlugin {
        dependsOn(installDependencies)

        // 确保二进制文件被包含在插件分发包中
        from("src/main/resources/server") {
            into("server")
        }
    }

    // 准备沙盒环境
    prepareSandbox {
        dependsOn(installDependencies)

        from("src/main/resources/server") {
            into("server")
        }

        from("src/main/resources/debugger") {
            into("debugger")
        }

    }

    // 清理任务
    clean {
        dependsOn(cleanDependencies)
    }
}
