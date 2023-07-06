package com.tang.intellij.lua.codeInsight.highlighting

/*
 * Copyright (c) 2017. tangzx(love.tangzx@qq.com)
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

import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactoryBase
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector
import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.Consumer
import com.tang.intellij.lua.codeInsight.LuaReadWriteAccessDetector
import com.tang.intellij.lua.psi.*

/**
 *
 * Created by tangzx on 2017/3/18.
 */
class LuaHighlightUsagesHandlerFactory : HighlightUsagesHandlerFactoryBase() {

    override fun createHighlightUsagesHandler(editor: Editor,
                                              psiFile: PsiFile,
                                              psiElement: PsiElement): HighlightUsagesHandlerBase<*>? {
        when(psiElement.node.elementType) {
            LuaTypes.RETURN -> {
                val returnStat = PsiTreeUtil.getParentOfType(psiElement, LuaReturnStat::class.java)
                if (returnStat != null) {
                    val funcBody = PsiTreeUtil.getParentOfType(returnStat, LuaFuncBody::class.java)
                    if (funcBody != null) {
                        return LuaHighlightExitPointsHandler(editor, psiFile, returnStat, funcBody)
                    }
                }
            }

            else -> {
                val parent = psiElement.parent
                val parentType = parent.node.elementType
                if (parentType == LuaTypes.BINARY_OP || parentType == LuaTypes.UNARY_OP) {
                    return object : HighlightUsagesHandlerBase<PsiElement>(editor, psiFile) {
                        override fun selectTargets(list: MutableList<out PsiElement>, consumer: Consumer<in MutableList<out PsiElement>>) {
                        }

                        override fun computeUsages(list: MutableList<out PsiElement>) {
                            addOccurrence(parent.parent)
                        }

                        override fun getTargets() = arrayListOf(psiElement)
                    }
                }
                else if (parent is LuaNameExpr || parent is LuaIndexExpr) {
                    return ReferenceHighlightHandler(editor, psiFile, parent)
                }
            }
        }
        return null
    }
}

/**
 * 由于新版本IDEA 202采用了新的Reference高亮算法，导致Lua高亮失效，这里自己处理高亮
 */
private class ReferenceHighlightHandler(editor: Editor, psiFile: PsiFile, val psi:PsiElement) : HighlightUsagesHandlerBase<PsiElement>(editor, psiFile) {

    private val list: List<PsiElement> by lazy {
        val result = mutableListOf<PsiElement>()
        val ref = psi.reference?.resolve() ?: psi
        if (ref.containingFile == psiFile)
            result.add(ref)
        val query = ReferencesSearch.search(ref, GlobalSearchScope.fileScope(psiFile))
        query.forEach {
            result.add(it.element)
        }
        result
    }

    override fun getTargets() = arrayListOf(psi)

    override fun selectTargets(list: MutableList<out PsiElement>, consumer: Consumer<in MutableList<out PsiElement>>) = Unit

    override fun computeUsages(list: MutableList<out PsiElement>) {
        val detector = LuaReadWriteAccessDetector()
        this.list.forEach { psi ->
            val rangeElement = (psi as? PsiNameIdentifierOwner)?.nameIdentifier ?: psi
            val range = InjectedLanguageManager.getInstance(this.psi.project).injectedToHost(rangeElement, rangeElement.textRange)
            val access = detector.getExpressionAccess(psi)
            if (access == ReadWriteAccessDetector.Access.Read)
                myReadUsages.add(range)
            else
                myWriteUsages.add(range)
        }
    }

    override fun highlightReferences() = false
}
