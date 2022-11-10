package com.tang.intellij.lua.document

import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.lang.documentation.DocumentationProvider
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.refactoring.suggested.startOffset
import org.wso2.lsp4intellij.editor.EditorEventManagerBase
import org.wso2.lsp4intellij.utils.FileUtils

class LuaDocumentationProvider : AbstractDocumentationProvider(), DocumentationProvider {

    override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
        if (element != null) {
            return generateDoc(element, originalElement)
        }
        return null
    }

    override fun getDocumentationElementForLookupItem(
        psiManager: PsiManager,
        obj: Any,
        element: PsiElement?
    ): PsiElement? {
        return element
    }

    override fun getCustomDocumentationElement(
        editor: Editor,
        file: PsiFile,
        contextElement: PsiElement?,
        targetOffset: Int
    ): PsiElement? {
        return contextElement
    }

    override fun getDocumentationElementForLink(psiManager: PsiManager, link: String, context: PsiElement?): PsiElement? {
        return context
    }

    override fun getUrlFor(element: PsiElement?, originalElement: PsiElement?): MutableList<String> {
        return mutableListOf()
    }

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? {
        val virtualFile = element.containingFile.virtualFile
        val uri = FileUtils.VFSToURI(virtualFile)
        val editorManager = EditorEventManagerBase.forUri(uri)
        return editorManager.generateDoc(element.startOffset)
    }

}