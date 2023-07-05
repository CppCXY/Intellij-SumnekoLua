//package com.tang.intellij.lua.editor.formatter
//
////import com.cppcxy.intellij.lua.psi.LuaFileUtil.getPluginVirtualFile
////import com.cppcxy.intellij.lua.psi.LuaPsiFile
//import com.intellij.configurationStore.NOTIFICATION_GROUP_ID
//import com.intellij.execution.ExecutionException
//import com.intellij.formatting.service.AsyncDocumentFormattingService
//import com.intellij.formatting.service.AsyncFormattingRequest
//import com.intellij.formatting.service.FormattingService
//import com.intellij.lang.LanguageFormatting
//import com.intellij.psi.PsiFile
//import org.wso2.lsp4intellij.IntellijLanguageClient
//import org.wso2.lsp4intellij.requests.ReformatAccept
//import org.wso2.lsp4intellij.requests.ReformatHandler
//import org.wso2.lsp4intellij.utils.FileUtils
//import java.util.*
//
//
//class EmmyLuaCodeStyle : AsyncDocumentFormattingService() {
//
//    private val FEATURES: MutableSet<FormattingService.Feature> = mutableSetOf(
//        FormattingService.Feature.FORMAT_FRAGMENTS
//    )
//
//    override fun getFeatures(): MutableSet<FormattingService.Feature> {
//        return FEATURES
//    }
//
//    override fun canFormat(file: PsiFile): Boolean {
//        return LanguageFormatting.INSTANCE.allForLanguage(file.language).isEmpty()
//                && IntellijLanguageClient.isExtensionSupported(file.virtualFile)
//    }
//
//    override fun createFormattingTask(request: AsyncFormattingRequest): FormattingTask? {
//        try {
//            return object : FormattingTask {
//                override fun run() {
//                    val uri = FileUtils.VFSToURI(request.context.virtualFile)
//                    val range = request.formattingRanges.first();
//                    if (range.startOffset == 0) {
//                        ReformatHandler.reformatFile(uri, object : ReformatAccept {
//                            override fun Accept(s: String?) {
//                                request.onTextReady(s!!);
//                            }
//
//                            override fun Reject() {
//                                request.onError("formatting error", "syntax error")
//                            }
//                        })
//                    } else {
//                        ReformatHandler.reformatSelection(uri, object : ReformatAccept {
//                            override fun Accept(s: String?) {
//                                request.onTextReady(s!!);
//                            }
//
//                            override fun Reject() {
//                                request.onError("formatting error", "syntax error")
//                            }
//                        })
//                    }
//                }
//
//                override fun cancel(): Boolean {
////                    handler.destroyProcess()
//                    return true
//                }
//
//                override fun isRunUnderProgress(): Boolean {
//                    return true
//                }
//            }
//
//        } catch (e: ExecutionException) {
//            e.message?.let { request.onError("EmmyLuaCodeStyle", it) };
//            return null;
//        }
//
//    }
//
//    override fun getNotificationGroupId(): String {
//        return NOTIFICATION_GROUP_ID
//    }
//
//    override fun getName(): String {
//        return "formatting"
//    }
//}