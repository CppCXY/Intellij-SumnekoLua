package com.cppcxy.ide.lsp

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.server.ProcessStreamConnectionProvider


class SumnekoServer(val project: Project) : ProcessStreamConnectionProvider() {
    init {
        if (!SumnekoAdaptor.canExecute) {
            SumnekoAdaptor.addExecutePermission()
        }

        val commands = listOf(SumnekoAdaptor.luaLanguageServer, "--locale=${SumnekoAdaptor.locale}")
        super.setCommands(commands)
    }
}