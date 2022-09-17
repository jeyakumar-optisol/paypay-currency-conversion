package io.paypay.utility.threading

import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher

fun runOnAsyncThread(callback: suspend CoroutineScope.() -> Unit): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        callback()
    }
}

fun runOnMainThread(callback: suspend CoroutineScope.() -> Unit): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        callback()
    }
}

fun runOnDefaultThread(callback: suspend CoroutineScope.() -> Unit): Job {
    return CoroutineScope(Dispatchers.Default).launch {
        callback()
    }
}

fun runOnCurrentThread(callback: suspend CoroutineScope.() -> Unit): Job {
    return CoroutineScope(Dispatchers.Unconfined).launch {
        callback()
    }
}

fun runOnDispatcher(
    dispatcher: CoroutineDispatcher,
    callback: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineScope(UnconfinedTestDispatcher()).launch {
        callback()
    }
}

private fun executeAndMainCallback(
    backgroundExecution: () -> Unit,
    mainThreadExecution: () -> Unit
): Job {
    return runOnAsyncThread {
        backgroundExecution()
        runOnMainThread {
            mainThreadExecution()
        }
    }
}