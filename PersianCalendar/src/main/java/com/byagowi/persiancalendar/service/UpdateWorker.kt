package com.byagowi.persiancalendar.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.byagowi.persiancalendar.utils.setChangeDateWorker
import com.byagowi.persiancalendar.utils.update
import com.byagowi.persiancalendar.utils.updateStoredPreference
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class UpdateWorker(val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result =
        withContext(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
            try {
                setChangeDateWorker(context)
                updateStoredPreference(applicationContext)
                update(applicationContext, true)
                Result.success()
            } catch (e: Throwable) {
                e.printStackTrace()
                Result.failure()
            }
        }
}
