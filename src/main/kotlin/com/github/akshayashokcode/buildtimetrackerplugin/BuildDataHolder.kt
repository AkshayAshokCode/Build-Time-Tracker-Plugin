package com.github.akshayashokcode.buildtimetrackerplugin

object BuildDataHolder {
    @Volatile
    var lastBuildDurationMs: Long = 0
}