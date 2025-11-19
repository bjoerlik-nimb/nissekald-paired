package com.nimbit.nissekald_paired.presentation

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MessageViewModel : ViewModel() {



    private val _uiMsgState = MutableStateFlow("")
    val uiMsgState: StateFlow<String> = _uiMsgState.asStateFlow()

    private val _uiBatteryState = MutableStateFlow(BatteryHelper.getBattery())
    val uiBatteryState: StateFlow<String> = _uiBatteryState.asStateFlow()

    fun updateMessage(message: String) {
        _uiMsgState.update { message };

    }

    fun resetMessage() {
        _uiMsgState.update { "" }
    }

    fun updateBattery() {
        _uiBatteryState.update {
            BatteryHelper.getBattery()
        }
    }
}