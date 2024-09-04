package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import android.content.Context
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gkprojects.cmmsandroidapp.CMMSDatabase
import com.gkprojects.cmmsandroidapp.Dao.SettingsDao
import com.gkprojects.cmmsandroidapp.DataClasses.Settings

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.gkprojects.cmmsandroidapp.DataClasses.Settings as AppSettings

class SettingsRepository private constructor(context: Context) {

    private val settingsDao: SettingsDao = CMMSDatabase.getInstance(context)!!.SettingsDao()

    companion object {
        @Volatile
        private var instance: SettingsRepository? = null

        fun getInstance(context: Context): SettingsRepository {
            return instance ?: synchronized(this) {
                instance ?: SettingsRepository(context).also { instance = it }
            }
        }
    }

    // Example method to insert settings
    suspend fun insertSettings(settings: AppSettings): Long {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
        settings.DateCreated = dateFormat.format(currentDateTime)
        settings.LastModified=dateFormat.format(currentDateTime)
        return settingsDao.insertSettings(settings)
    }

    // Example method to retrieve settings by key
    suspend fun getSettingsByKey(key: String): List<AppSettings> {
        return settingsDao.getSettingsByKey(key)
    }
    suspend fun deleteSettings(settings: Settings):Int{
        return settingsDao.deleteSettings(settings)
    }
}
