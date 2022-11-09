package com.tang.intellij.lua.lang;

import com.intellij.lang.Language;

public class LuaLanguage extends Language {
    public static final LuaLanguage INSTANCE = new LuaLanguage();

    public static final int INDEX_VERSION = 38;

    private LuaLanguage() {
        super("Lua");
    }
}