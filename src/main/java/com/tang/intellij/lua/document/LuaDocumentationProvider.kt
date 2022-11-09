package com.tang.intellij.lua.document

import com.intellij.codeInsight.documentation.DocumentationManagerUtil
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.lang.documentation.DocumentationProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.startOffset
import org.wso2.lsp4intellij.editor.EditorEventManagerBase
import org.wso2.lsp4intellij.utils.FileUtils

class LuaDocumentationProvider: AbstractDocumentationProvider(), DocumentationProvider {

    override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
        return super<AbstractDocumentationProvider>.getQuickNavigateInfo(element, originalElement)
    }

    override fun getDocumentationElementForLookupItem(psiManager: PsiManager, obj: Any, element: PsiElement?): PsiElement? {
        return super<AbstractDocumentationProvider>.getDocumentationElementForLookupItem(psiManager, obj, element)
    }

//    override fun getDocumentationElementForLink(psiManager: PsiManager, link: String, context: PsiElement?): PsiElement? {
////        return LuaClassIndex.find(link, SearchContext.get(psiManager.project))
//        TODO()
//    }

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? {
        val virtualFile = element.containingFile.virtualFile
        val uri = FileUtils.VFSToURI(virtualFile)
        val editorManager = EditorEventManagerBase.forUri(uri)
        return editorManager.generateDoc(element.startOffset)
    }

}