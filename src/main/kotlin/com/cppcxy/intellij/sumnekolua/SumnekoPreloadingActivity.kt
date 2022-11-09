package com.cppcxy.intellij.sumnekolua

import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.progress.ProgressIndicator
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.ProcessBuilderServerDefinition
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition
import java.io.File

class SumnekoPreloadingActivity : PreloadingActivity() {
    override fun preload(indicator: ProgressIndicator) {
        val pb = ProcessBuilder("C:/Users/zc/Desktop/github/Intellij-SumnekoLua/src/main/resources/server/bin/lua-language-server.exe",
            "--logpath=C:\\Users\\zc\\Desktop\\test",
            "--develop=true",
            "--dbgport=11429"
            )
            .directory(File("C:/Users/zc/Desktop/github/Intellij-SumnekoLua/src/main/resources/server"))
        IntellijLanguageClient.addServerDefinition(
            ProcessBuilderServerDefinition(
                "lua", pb
            )
        );
    }
}