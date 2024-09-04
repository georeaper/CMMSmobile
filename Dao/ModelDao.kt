package com.gkprojects.cmmsandroidapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gkprojects.cmmsandroidapp.DataClasses.ModelAsset

@Dao
interface ModelDao {

    @Query("Select * from ModelAsset")
    suspend fun selectAllModels():List<ModelAsset>
    @Insert
    suspend fun insertModel(model :ModelAsset):Long

    @Delete
    suspend fun deleteModel(model: ModelAsset):Int
}