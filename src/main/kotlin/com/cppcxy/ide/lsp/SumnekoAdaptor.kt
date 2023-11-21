package com.cppcxy.ide.lsp

import com.cppcxy.ide.setting.SumnekoSettings
import com.cppcxy.ide.setting.SumnekoSupportLocale
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.util.SystemInfoRt
import java.io.File
import java.util.*

object SumnekoAdaptor {
    private val pluginSource: String?
        get() = PluginManagerCore.getPlugin(PluginId.getId("com.cppcxy.Intellij-SumnekoLua"))?.pluginPath?.toFile()?.path

    private val exe: String
        get() {
            return if (SystemInfoRt.isWindows) {
                "win32-x64/bin/lua-language-server.exe"
            } else if (SystemInfoRt.isMac) {
                if (System.getProperty("os.arch") == "arm64") {
                    "darwin-arm64/bin/lua-language-server"
                } else {
                    "darwin-x64/bin/lua-language-server"
                }
            } else {
                "linux-x64/bin/lua-language-server"
            }
        }

    val luaLanguageServer: String
        get() {
            if (SumnekoSettings.getInstance().location.isNotEmpty()) {
                return SumnekoSettings.getInstance().location
            }
            return "$pluginSource/server/$exe"
        }

    val locale: String
        get() {
            if (SumnekoSettings.getInstance().locale != SumnekoSupportLocale.auto) {
                return SumnekoSettings.getInstance().locale.toString()
            }
            val locale = Locale.getDefault()
            val languageCode = locale.language.lowercase()
            val countryCode = locale.country.lowercase()
            return "${languageCode}-${countryCode}"
        }

    val canExecute: Boolean
        get() {
            val file = File(luaLanguageServer)
            return file.exists() && file.canExecute()
        }

    fun addExecutePermission() {
        val file = File(luaLanguageServer)
        if (file.exists() && !file.canExecute()) {
            val runtime = Runtime.getRuntime()
            val process = runtime.exec(arrayOf("chmod", "+x", file.absolutePath))
            process.waitFor()
        }
    }
}
