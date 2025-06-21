package com.github.akshayashokcode.buildtimetrackerplugin.startup

import com.github.akshayashokcode.buildtimetrackerplugin.BuildListener
import com.intellij.compiler.server.BuildManagerListener.TOPIC
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class MyProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        project.messageBus
            .connect()
            .subscribe(TOPIC, BuildListener())
    }
}