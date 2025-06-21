package com.github.akshayashokcode.buildtimetrackerplugin


import com.intellij.compiler.server.BuildManagerListener
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class BuildListener: BuildManagerListener {

    private val logger = Logger.getInstance(BuildListener::class.java)
    private val startTimes = ConcurrentHashMap<UUID, Long>()

    override fun buildStarted(project: Project, sessionId: UUID, isAutomake: Boolean) {
        val startTime = System.currentTimeMillis()
        startTimes[sessionId] = startTime
        logger.info("Build started. Session: $sessionId | Project: ${project.name} | Type: ${if (isAutomake) "Automake" else "Manual"} | Time: $startTime")
    }

    override fun buildFinished(project: Project, sessionId: UUID, isAutomake: Boolean) {
        val endTime = System.currentTimeMillis()
        val startTime = startTimes.remove(sessionId)
        val duration = if (startTime != null) endTime - startTime else -1
        logger.info("Build finished. Session: $sessionId | Project: ${project.name} | Time: $endTime")
        logger.info("Build duration: ${if (duration >= 0) "$duration ms" else "Unknown (start time missing)"}")
    }
}