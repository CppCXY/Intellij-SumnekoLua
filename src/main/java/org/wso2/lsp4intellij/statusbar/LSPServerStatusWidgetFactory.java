/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.lsp4intellij.statusbar;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LSPServerStatusWidgetFactory implements StatusBarWidgetFactory {
    private final Map<Project, LSPServerStatusWidget> widgetForProject = new HashMap<>();

    @Override
    public @NonNls
    @NotNull
    String getId() {
        return "LSP";
    }

    @Override
    public @Nls
    @NotNull
    String getDisplayName() {
        return "Language Server";
    }

    @Override
    public boolean isAvailable(@NotNull Project project) {
        return true;
    }

    @Override
    public
    StatusBarWidget createWidget(@NotNull Project project) {
        return widgetForProject.computeIfAbsent(project, (k) -> new LSPServerStatusWidget(project));
    }

    @Override
    public void disposeWidget(@NotNull StatusBarWidget statusBarWidget) {
        if (statusBarWidget instanceof LSPServerStatusWidget) {
            widgetForProject.remove(((LSPServerStatusWidget) statusBarWidget).getProject());
        }
    }

    @Override
    public boolean canBeEnabledOn(@NotNull StatusBar statusBar) {
        return true;
    }

    public LSPServerStatusWidget getWidget(Project project) {
        return widgetForProject.get(project);
    }
}
