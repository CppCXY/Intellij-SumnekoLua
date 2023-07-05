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

package com.tang.intellij.lua.codeInsight

import com.intellij.codeInsight.hints.HintInfo
import com.intellij.codeInsight.hints.InlayInfo
import com.intellij.codeInsight.hints.InlayParameterHintsProvider
import com.intellij.codeInsight.hints.Option
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.tang.intellij.lua.psi.*
import java.util.*

/**

 * Created by TangZX on 2016/12/14.
 */
class LuaParameterHintsProvider : InlayParameterHintsProvider {
    companion object {
        private val ARGS_HINT = Option("lua.hints.show_args_type",
                "Show argument name hints",
                true)

        private val LOCAL_VARIABLE_HINT = Option("lua.hints.show_local_var_type",
                "Show local variable type hints",
                false)
        private val PARAMETER_TYPE_HINT = Option("lua.hints.show_parameter_type",
                "Show parameter type hints",
                false)
        private val FUNCTION_HINT = Option("lua.hints.show_function_type",
                "Show function return type hints",
                false)
        private const val TYPE_INFO_PREFIX = "@TYPE@"
        private var EXPR_HINT = arrayOf(LuaLiteralExpr::class.java,
                LuaBinaryExpr::class.java,
                LuaUnaryExpr::class.java,
                LuaClosureExpr::class.java,
                LuaTableExpr::class.java)
    }

    override fun getParameterHints(psi: PsiElement): List<InlayInfo> {
        val list = ArrayList<InlayInfo>()
        return list
    }

    override fun getHintInfo(psiElement: PsiElement): HintInfo? = null

    override fun getDefaultBlackList(): Set<String> {
        return HashSet()
    }

    override fun isBlackListSupported() = false

    override fun getSupportedOptions(): List<Option> {
        return listOf(ARGS_HINT, LOCAL_VARIABLE_HINT, PARAMETER_TYPE_HINT, FUNCTION_HINT)
    }

    override fun getInlayPresentation(inlayText: String): String {
        if (inlayText.startsWith(TYPE_INFO_PREFIX)) {
            return " : ${inlayText.substring(TYPE_INFO_PREFIX.length)}"
        }
        return "$inlayText : "
    }
}
