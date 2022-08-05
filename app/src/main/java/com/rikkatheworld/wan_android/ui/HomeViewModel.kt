package com.rikkatheworld.wan_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rikkatheworld.wan_android.ui.main.CourseTabs

class HomeViewModel : ViewModel() {
    fun onPositionChanged(tab: CourseTabs) {
    }

    private val _position = MutableLiveData<CourseTabs>()
    val position: LiveData<CourseTabs>
        get() = _position
}