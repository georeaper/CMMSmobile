package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gkprojects.cmmsandroidapp.Models.CategoryViewModel

class CategoryViewModelFactory(private val repository: CategoryRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}