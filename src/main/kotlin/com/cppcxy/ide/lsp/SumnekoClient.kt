package com.cppcxy.ide.lsp

import com.cppcxy.ide.editor.statusbar.StatusBarWidgetFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.WindowManager
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification

class SumnekoClient(private val serverNotificationsHandler: LspServerNotificationsHandler, val project: Project) :
    Lsp4jClient(serverNotificationsHandler) {

    @JsonNotification("$/status/show")
    fun statusShow() {
    }

    @JsonNotification("$/status/hide")
    fun statusHide() {
    }

    @JsonNotification("$/status/report")
    fun statusReport(report: StatusReport) {
        val statusBar = WindowManager.getInstance().getStatusBar(project)
        val widget = statusBar.getWidget("SumnekoBar") as StatusBarWidgetFactory.SumnekoBar?
        if (widget != null) {
            widget.message = report.text
            widget.toolTip = report.tooltip
        }
    }
}