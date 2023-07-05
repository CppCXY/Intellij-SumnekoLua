@file:Suppress("UnstableApiUsage")

package com.cppcxy.ide.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.tang.intellij.lua.lang.LuaFileType
import com.intellij.platform.lsp.api.LspServerSupportProvider

class SumnekoLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        if (file.fileType !is LuaFileType) return

        serverStarter.ensureServerStarted(SumnekoLspServerDescriptor(project))
    }
}