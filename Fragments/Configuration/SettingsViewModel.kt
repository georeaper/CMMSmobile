package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.gkprojects.cmmsandroidapp.DataClasses.Settings as AppSettings


class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {

    // LiveData for insert success status
    private val _insertSuccess = MutableLiveData<Boolean>()
    val insertSuccess: LiveData<Boolean> get() = _insertSuccess

    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> get() = _deleteSuccess
    // LiveData for loaded settings
    private val _settingsData = MutableLiveData<List<AppSettings>>()
    val settingsData: LiveData<List<AppSettings>> get() = _settingsData

    // LiveData for error messages
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Function to insert settings
    fun insertSettings(settings: AppSettings) {
        viewModelScope.launch {
            try {
                val result = repository.insertSettings(settings)
                _insertSuccess.value = result > 0
            } catch (e: Exception) {
                _insertSuccess.value = false
                _error.value = e.message // Handle error if needed
            }
        }
    }

    // Function to load settings by key
    fun loadSettingsByKey(key: String) {
        viewModelScope.launch {
            try {
                val result = repository.getSettingsByKey(key)
                _settingsData.value = result // Update LiveData with the fetched data
            } catch (e: Exception) {
                _error.value = e.message // Handle error if needed
            }
        }
    }
    fun deleteSettings(settings: AppSettings){
        viewModelScope.launch {
            try {
                val deletion = repository.deleteSettings(settings)
                _deleteSuccess.value= deletion>0
            }catch (e:Exception){
                _deleteSuccess.value=false
                _error.value=e.message

            }
        }
    }
}
