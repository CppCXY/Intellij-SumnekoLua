@file:Suppress("UnstableApiUsage")

package com.cppcxy.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.LspCodeActionsSupport
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import com.tang.intellij.lua.lang.LuaFileType
import com.tang.intellij.lua.lang.LuaIcons
import org.eclipse.lsp4j.CompletionItem
import org.eclipse.lsp4j.CompletionItemKind
import org.eclipse.lsp4j.InitializeParams
import javax.swing.Icon


class SumnekoLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "SumnekoLua") {
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

    override val lspCompletionSupport = object : LspCompletionSupport() {
        override fun getIcon(item: CompletionItem): Icon? {
            return when (item.kind) {
                CompletionItemKind.Function -> LuaIcons.CLASS_METHOD
                CompletionItemKind.Class -> LuaIcons.CLASS
                CompletionItemKind.Field -> LuaIcons.CLASS_FIELD
                CompletionItemKind.File -> LuaIcons.FILE
                else -> {
                    super.getIcon(item)
                }
            }

        }
    }
}