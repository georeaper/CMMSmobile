package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider



class ModelViewModelFactory(    private val repository: ModelRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModelViewModel::class.java)) {
            return ModelViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}