package com.gkprojects.cmmsandroidapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.gkprojects.cmmsandroidapp.DataClasses.Settings as AppSettings

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: AppSettings): Long

    @Query("Select * from Settings")
     fun getAllSettings(): LiveData<List<AppSettings>>

    @Query("Select * from Settings where SettingsKey= :key ")
    suspend fun getSettingsByKey(key:String):List<AppSettings>

   @Delete
    suspend fun deleteSettings(settings: AppSettings):Int

}