package com.example.quoteplus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteplus.domain.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): ViewModel() {

    suspend fun getToken(): String {
        return preferenceStorage.getToken().first()
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            preferenceStorage.saveToken(token)
        }
    }

}