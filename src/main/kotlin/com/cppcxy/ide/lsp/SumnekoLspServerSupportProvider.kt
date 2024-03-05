@file:Suppress("UnstableApiUsage")

package com.cppcxy.ide.lsp


import com.cppcxy.ide.setting.SumnekoSettingsPanel
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServer
import com.tang.intellij.lua.lang.LuaFileType
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.lsWidget.LspServerWidgetItem
import com.tang.intellij.lua.lang.LuaIcons

class SumnekoLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        if (file.fileType !is LuaFileType) return

        serverStarter.ensureServerStarted(SumnekoLspServerDescriptor(project))
    }

    override fun createLspServerWidgetItem(lspServer: LspServer, currentFile: VirtualFile?): LspServerWidgetItem =
        LspServerWidgetItem(lspServer, currentFile, LuaIcons.FILE, SumnekoSettingsPanel::class.java)
}