@file:Suppress("UnstableApiUsage")

package com.cppcxy.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.LspCodeActionsSupport
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import com.tang.intellij.lua.lang.LuaFileType
import org.eclipse.lsp4j.InitializeParams


class SumnekoLspServerDescriptor(project: Project): ProjectWideLspServerDescriptor(project, "SumnekoLua") {
    override fun isSupportedFile(file: VirtualFile): Boolean {
        return file.fileType is LuaFileType
    }

    override fun createCommandLine(): GeneralCommandLine {
        return GeneralCommandLine().apply {
            withCharset(Charsets.UTF_8)
            withExePath(SumnekoAdaptor.luaLanguageServer)
        }
    }

    override fun createInitializeParams(): InitializeParams {
        return super.createInitializeParams()
    }
}