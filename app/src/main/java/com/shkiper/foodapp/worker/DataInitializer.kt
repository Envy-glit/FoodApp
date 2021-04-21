package com.shkiper.foodapp.worker
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.shkiper.foodapp.room.database.AppDatabase
import com.shkiper.foodapp.room.entity.Food

class DataInitializer(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d(TAG, " >>> Starting initializing data in database")
        var result = Result.failure()
        try {
            applicationContext.assets.open(FOOD_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Food>>() {}.type
                    val plantList: List<Food> = Gson().fromJson(jsonReader, plantType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database!!.foodDao().insertAll(plantList)

                    result = Result.success()
                    Log.d(TAG, " >>> Data initialization success")
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error in data initialization into database", ex)
            result = Result.failure()
        }

        return result
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        private const val FOOD_DATA_FILENAME = "foods.json"
    }
}