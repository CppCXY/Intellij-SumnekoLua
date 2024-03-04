@file:Suppress("UnstableApiUsage")

package com.cppcxy.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.*
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import com.intellij.platform.lsp.api.customization.LspFormattingSupport
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
        if (!SumnekoAdaptor.canExecute) {
            SumnekoAdaptor.addExecutePermission()
        }

        return GeneralCommandLine().apply {
            withCharset(Charsets.UTF_8)
            withExePath(SumnekoAdaptor.luaLanguageServer)
            addParameter("--locale=${SumnekoAdaptor.locale}")
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
                CompletionItemKind.Enum -> LuaIcons.ENUM
                CompletionItemKind.Snippet -> LuaIcons.SNIPPET
                else -> {
                    super.getIcon(item)
                }
            }

        }
    }

    override val lspFormattingSupport = object : LspFormattingSupport() {
        override fun shouldFormatThisFileExclusivelyByServer(
            file: VirtualFile,
            ideCanFormatThisFileItself: Boolean,
            serverExplicitlyWantsToFormatThisFile: Boolean
        ): Boolean {
            return file.fileType is LuaFileType
        }
    }
}




