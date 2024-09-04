package com.gkprojects.cmmsandroidapp.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gkprojects.cmmsandroidapp.DataClasses.Users

class SharedViewModel: ViewModel() {
    val reportId = MutableLiveData<String>()
    val customerId =MutableLiveData<String>()
    val user =MutableLiveData<Users>()
    val fininalizedUser =MutableLiveData<Users>()
}