package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import android.content.Context
import com.gkprojects.cmmsandroidapp.CMMSDatabase
import com.gkprojects.cmmsandroidapp.Dao.ModelDao
import com.gkprojects.cmmsandroidapp.DataClasses.ModelAsset
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ModelRepository private constructor(context: Context) {
    private val modelDao : ModelDao = CMMSDatabase.getInstance(context)!!.ModelDao()

    companion object {
        @Volatile
        private var instance: ModelRepository? = null

        fun getInstance(context: Context): ModelRepository {
            return instance ?: synchronized(this) {
                instance ?: ModelRepository(context).also { instance = it }
            }
        }
    }
    suspend fun insertModel(model : ModelAsset): Long {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
        model.DateCreated = dateFormat.format(currentDateTime)
        model.LastModified=dateFormat.format(currentDateTime)
        return modelDao.insertModel(model)
    }
    suspend fun getAllModels(): List<ModelAsset> {
        return modelDao.selectAllModels()
    }
    suspend fun deleteModels(model : ModelAsset):Int{
        return modelDao.deleteModel(model)
    }
}