/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.lsp4intellij.contributors

import com.intellij.codeInsight.completion.*
import com.intellij.openapi.application.ex.ApplicationUtil
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.progress.ProgressIndicatorProvider
import com.intellij.util.ProcessingContext
import com.tang.intellij.lua.psi.LuaLabelStat
import com.tang.intellij.lua.psi.LuaPsiFile
import org.wso2.lsp4intellij.editor.EditorEventManagerBase
import org.wso2.lsp4intellij.utils.DocumentUtils
import java.util.*

/**
 * The completion contributor for the LSP
 */
internal class LSPCompletionContributor : CompletionContributor() {
    private var suggestWords = true

    override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
        val provider: CompletionProvider<CompletionParameters> = object : CompletionProvider<CompletionParameters>() {
            override fun addCompletions(
                parameters: CompletionParameters,
                context: ProcessingContext,
                result: CompletionResultSet
            ) {
                try {
                    Objects.requireNonNull(ProgressIndicatorProvider.getGlobalProgressIndicator())?.let {
                        ApplicationUtil.runWithCheckCanceled<Any?>({
                            val editor = parameters.editor
                            val offset = parameters.offset
                            val serverPos = DocumentUtils.offsetToLSPPos(editor, offset)
                            val manager = EditorEventManagerBase.forEditor(editor)
                            if (manager != null) {
                                result.addAllElements(manager.completion(serverPos))
                            }
                            null
                        }, it)
                    }
                } catch (ignored: ProcessCanceledException) {
                    // ProcessCanceledException can be ignored.
                } catch (e: Exception) {
                    LOG.warn("LSP Completions ended with an error", e)
                }
            }
        }
        val editor = parameters.editor
        val offset = parameters.offset
        val manager = EditorEventManagerBase.forEditor(editor)
        if (manager != null) {
            val prefix = manager.getCompletionPrefix(editor, offset)
            provider.addCompletionVariants(
                parameters,
                ProcessingContext(),
                result.withPrefixMatcher(PlainPrefixMatcher(prefix))
            )
            if (result.isStopped) {
                return
            }
            super.fillCompletionVariants(parameters, result)
        }
    }

    override fun beforeCompletion(context: CompletionInitializationContext) {
        suggestWords = true
        val file = context.file
        if (file is LuaPsiFile) {
            val element = file.findElementAt(context.caret.offset - 1)
            if (element != null) {
                if (element.parent is LuaLabelStat) {
                    suggestWords = false
                    context.dummyIdentifier = ""
                }
            }
        }
    }

    companion object {
        private val LOG = Logger.getInstance(
            LSPCompletionContributor::class.java
        )
    }
}