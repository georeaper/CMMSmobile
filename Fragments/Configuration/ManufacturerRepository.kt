package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import android.content.Context
import com.gkprojects.cmmsandroidapp.CMMSDatabase
import com.gkprojects.cmmsandroidapp.Dao.ManufacturerDao
import com.gkprojects.cmmsandroidapp.DataClasses.Manufacturer

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ManufacturerRepository private constructor(context: Context) {
    private val manufacturerDao : ManufacturerDao = CMMSDatabase.getInstance(context)!!.ManufacturerDao()

    companion object {
        @Volatile
        private var instance: ManufacturerRepository? = null

        fun getInstance(context: Context): ManufacturerRepository {
            return instance ?: synchronized(this) {
                instance ?: ManufacturerRepository(context).also { instance = it }
            }
        }
    }
    suspend fun insertManufacturer(manufacturer: Manufacturer): Long {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
        manufacturer.DateCreated = dateFormat.format(currentDateTime)
        manufacturer.LastModified=dateFormat.format(currentDateTime)
        return manufacturerDao.insertManufacturer(manufacturer)
    }
    suspend fun getAllManufacturer(): List<Manufacturer> {
        return manufacturerDao.selectAllManufacturer()
    }
    suspend fun deleteManufacturer(manufacturer: Manufacturer):Int{
        return manufacturerDao.deleteManufacturer(manufacturer)
    }
}