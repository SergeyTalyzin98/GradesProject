package com.grades.project.worker

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.jvm.Throws

class SyncWithServerWorker(
    private val context: Context,
    workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {

    companion object {
        val TAG: String = SyncWithServerWorker::class.java.name
        val NAME: String = "name"
        val DATA_KEY = "data_key"
    }

    override fun doWork(): Result {
        val data: String = inputData.getString(DATA_KEY) ?: return Result.failure()

        runCatching { syncWithServer(data) }.fold(
            onSuccess = { newData: String ->
                rescheduleSync(newData)
                return Result.success()
            },
            onFailure = {
                return Result.retry()
            }
        )
    }

    @Throws(Exception::class)
    private fun syncWithServer(data: String): String {
        return ""
    }

    private fun rescheduleSync(newData: String) {
        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                NAME,
                ExistingWorkPolicy.REPLACE,
                MyWorkRequestFactory.create(newData),
            )
    }
}