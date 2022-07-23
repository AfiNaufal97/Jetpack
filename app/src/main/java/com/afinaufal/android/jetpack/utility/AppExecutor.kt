package com.afinaufal.androiddasar.afinaufalsubmission_1.utility

import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import android.os.Handler

class AppExecutor @VisibleForTesting constructor(
    private val dataIO:Executor,
    private val connectIO:Executor,
    private val mainThread:Executor
) {
    companion object{
        private const val COUNT_OF_THREAD = 3
    }

    constructor():this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(COUNT_OF_THREAD),
        MainExecutor()
    )

    fun dataIO():Executor = dataIO
    fun connectIO():Executor = connectIO
    fun mainThread():Executor = mainThread

    private class MainExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            handler.post(command)
        }
    }
}