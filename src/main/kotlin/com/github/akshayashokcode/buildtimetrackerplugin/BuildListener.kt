package com.github.akshayashokcode.buildtimetrackerplugin


import com.intellij.compiler.server.BuildManagerListener
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class BuildListener: BuildManagerListener {

    private val buildStartTimes = mutableMapOf<UUID, Long>()

    override fun buildStarted(project: Project, sessionId: UUID, isAutomake: Boolean) {
        buildStartTimes[sessionId] = System.currentTimeMillis()
    }

    override fun buildFinished(project: Project, sessionId: UUID, isAutomake: Boolean) {
        val duration = System.currentTimeMillis() - (buildStartTimes[sessionId] ?: return)
        BuildDataHolder.lastBuildDurationMs = duration
    }
}