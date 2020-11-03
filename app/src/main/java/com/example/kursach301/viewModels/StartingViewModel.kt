package com.example.kursach301.viewModels

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import splitties.permissions.requestPermission

class StartingViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext





    fun requestPermissionsInFragment(fragment: Fragment) {
    viewModelScope.launch {
        fragment.requestPermission(android.Manifest.permission.BLUETOOTH)
        fragment.requestPermission(android.Manifest.permission.BLUETOOTH_ADMIN)
        fragment.requestPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

}