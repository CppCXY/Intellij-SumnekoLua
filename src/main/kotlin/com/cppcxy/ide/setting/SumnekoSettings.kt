package com.cppcxy.ide.setting

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

enum class SumnekoSupportLocale(val inner: String) {
    auto("auto"),
    en_US("en-us"),
    zh_CN("zh-cn"),
    zh_TW("zh-tw"),
    pt_BR("pt-br");

    override fun toString(): String {
        return inner
    }
}


@State(name = "SumnekoSettings", storages = [Storage("sumneko.xml")])
class SumnekoSettings : PersistentStateComponent<SumnekoSettings> {
    var locale = SumnekoSupportLocale.auto
    var location = ""

    companion object {
        @JvmStatic
        fun getInstance(): SumnekoSettings {
            return ApplicationManager.getApplication().getService(SumnekoSettings::class.java)
        }
    }

    override fun getState(): SumnekoSettings {
        return this
    }

    override fun loadState(state: SumnekoSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }
}