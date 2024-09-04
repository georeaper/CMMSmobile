package com.gkprojects.cmmsandroidapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gkprojects.cmmsandroidapp.DataClasses.Manufacturer

@Dao
interface ManufacturerDao {

    @Query("Select * from Manufacturer")
    suspend fun selectAllManufacturer():List<Manufacturer>

    @Insert
    suspend fun insertManufacturer(manufacturer : Manufacturer):Long

    @Delete
    suspend fun deleteManufacturer(manufacturer: Manufacturer) :Int
}