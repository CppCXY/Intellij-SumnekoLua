package com.cppcxy.ide.lsp

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.util.SystemInfoRt

object SumnekoAdaptor {
    private val pluginSource: String?
        get() = PluginManagerCore.getPlugin(PluginId.getId("com.cppcxy.Intellij-SumnekoLua"))?.pluginPath?.toFile()?.path

    private val exe: String
        get() {
            return if (SystemInfoRt.isWindows) {
                "lua-language-server.exe"
            } else {
                "lua-language-server"
            }
        }

    val luaLanguageServer: String = "$pluginSource/server/bin/$exe"

    private val project: Project
        get() = ProjectManager.getInstance().openProjects.first()

}
