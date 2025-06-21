package com.github.akshayashokcode.buildtimetrackerplugin.toolWindow

import com.github.akshayashokcode.buildtimetrackerplugin.BuildDataHolder
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.github.akshayashokcode.buildtimetrackerplugin.MyBundle
import com.github.akshayashokcode.buildtimetrackerplugin.services.MyProjectService
import javax.swing.JButton


class MyToolWindowFactory : ToolWindowFactory {


    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val label = JBLabel("Last Build Duration: ${BuildDataHolder.lastBuildDurationMs} ms")

        val panel = javax.swing.JPanel()
        panel.add(label)

        val content = ContentFactory.getInstance().createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)

        // Optionally add a timer to refresh the label every second
        javax.swing.Timer(1000) {
            label.text = "Last Build Duration: ${BuildDataHolder.lastBuildDurationMs} ms"
        }.start()
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel(MyBundle.message("randomLabel", "?"))

            add(label)
            add(JButton(MyBundle.message("shuffle")).apply {
                addActionListener {
                    label.text = MyBundle.message("randomLabel", service.getRandomNumber())
                }
            })
        }
    }
}
