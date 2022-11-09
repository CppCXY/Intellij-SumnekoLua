package com.tang.intellij.lua.lang;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
public class LuaIcons {
    private static Icon getIcon(String path) {
        return IconLoader.getIcon(path, LuaIcons.class);
    }

    public static final Icon FILE = getIcon("/icons/lua.png");
}
