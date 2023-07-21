package com.tang.intellij.lua.actions

import com.intellij.ide.fileTemplates.FileTemplateDescriptor
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory
import com.tang.intellij.lua.lang.LuaIcons


/**
 * @author : cofe
 * @date : 2021-12-10
 */
class LuaFileTemplateProvider : FileTemplateGroupDescriptorFactory {
    override fun getFileTemplatesDescriptor(): FileTemplateGroupDescriptor {
        val group = FileTemplateGroupDescriptor("Lua", LuaIcons.FILE)
        group.addTemplate(FileTemplateDescriptor("NewLua.lua", LuaIcons.FILE))
        return group
    }
}