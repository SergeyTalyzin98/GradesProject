package com.grades.project.worker

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

object SyncWithServerWorkerCreator {

    fun create(context: Context) {
        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                SyncWithServerWorker.NAME,
                ExistingWorkPolicy.REPLACE,
                MyWorkRequestFactory.create("some data"),
            )
    }
}