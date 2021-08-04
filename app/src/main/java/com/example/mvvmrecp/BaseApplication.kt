package com.example.mvvmrecp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication :Application(){
val isdark= mutableStateOf(false)
    fun toggleLightTheme(){
        isdark.value = !isdark.value
    }
}