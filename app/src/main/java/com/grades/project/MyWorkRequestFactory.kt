package com.grades.project

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import java.util.Calendar
import java.util.concurrent.TimeUnit

object MyWorkRequestFactory {

    fun create(data: String): OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<SyncWithServerWorker>()
            .setConstraints(createConstraints())
            .setInitialDelay(calculateDurationInMillis(), TimeUnit.MILLISECONDS)
            .addTag(SyncWithServerWorker.TAG)
            .setInputData(createInputData(data))
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                10,
                TimeUnit.SECONDS
            )
            .build()
    }

    private fun createInputData(data: String): Data {
        return Data.Builder()
            .putString(SyncWithServerWorker.DATA_KEY, data)
            .build()
    }

    private fun createConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
    }

    /**
     * Вычисляем необходимую задержку для запуска воркера, чтобы он запустился в 18 часов.
     * Если текущее время больше 18 часов, воркер запустится на следующий день.
     */
    private fun calculateDurationInMillis(): Long {
        val currentDate: Calendar = Calendar.getInstance()
        val desiredDate: Calendar = Calendar.getInstance()

        desiredDate.set(Calendar.HOUR_OF_DAY, 18)
        desiredDate.set(Calendar.MINUTE, 0)
        desiredDate.set(Calendar.SECOND, 0)

        if (desiredDate.before(currentDate)) {
            desiredDate.add(Calendar.HOUR_OF_DAY, 24)
        }
        return desiredDate.timeInMillis - currentDate.timeInMillis
    }
}