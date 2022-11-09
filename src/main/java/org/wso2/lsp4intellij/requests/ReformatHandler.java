/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.lsp4intellij.requests;

import com.intellij.openapi.editor.Editor;
import org.wso2.lsp4intellij.editor.EditorEventManager;
import org.wso2.lsp4intellij.editor.EditorEventManagerBase;

public class ReformatHandler {

    /**
     * Reformat a file given its editor
     *
     */
    public static void reformatFile(String uri, ReformatAccept accept) {
        EditorEventManager eventManager = EditorEventManagerBase.forUri(uri);
        if (eventManager != null) {
            eventManager.reformat(accept);
        }
    }

    /**
     * Reformat a selection in a file given its editor
     *
     */
    public static void reformatSelection(String uri, ReformatAccept accept) {
        EditorEventManager eventManager = EditorEventManagerBase.forUri(uri);
        if (eventManager != null) {
            eventManager.reformatSelection(accept);
        }
    }
}
