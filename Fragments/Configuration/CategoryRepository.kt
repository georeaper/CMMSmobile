package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import android.content.Context
import com.gkprojects.cmmsandroidapp.CMMSDatabase
import com.gkprojects.cmmsandroidapp.Dao.CategoryDao
import com.gkprojects.cmmsandroidapp.DataClasses.CategoryAsset
import com.gkprojects.cmmsandroidapp.DataClasses.Settings
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CategoryRepository private constructor(context: Context) {
    private val categoryDao :CategoryDao=CMMSDatabase.getInstance(context)!!.CategoryDao()

    companion object {
        @Volatile
        private var instance: CategoryRepository? = null

        fun getInstance(context: Context): CategoryRepository {
            return instance ?: synchronized(this) {
                instance ?: CategoryRepository(context).also { instance = it }
            }
        }
    }
    suspend fun insertCategory(category :CategoryAsset): Long {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
        category.DateCreated = dateFormat.format(currentDateTime)
        category.LastModified=dateFormat.format(currentDateTime)
        return categoryDao.insertCategories(category)
    }
    suspend fun getAllCategories(): List<CategoryAsset> {
        return categoryDao.selectAllCategories()
    }
    suspend fun deleteCategories(category :CategoryAsset):Int{
        return categoryDao.deleteCategories(category)
    }
}