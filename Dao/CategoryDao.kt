package com.gkprojects.cmmsandroidapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gkprojects.cmmsandroidapp.DataClasses.CategoryAsset

@Dao
interface CategoryDao {
    @Query("Select * from CategoryAsset")
    suspend fun selectAllCategories():List<CategoryAsset>

    @Insert
    suspend fun insertCategories(category : CategoryAsset):Long

    @Delete
    suspend fun deleteCategories(category: CategoryAsset):Int

}