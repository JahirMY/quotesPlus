package com.example.quoteplus.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.quoteplus.QuotesApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@SuppressLint("StaticFieldLeak")
object DataStoreManager {

    private const val USERDATASTORE = "quotes"
    private lateinit var context: Context
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USERDATASTORE)
    val TOKEN = stringPreferencesKey("TOKEN")

    // Method to initialize context
    fun initialize(context: Context) {
        this.context = context.applicationContext
    }
    suspend fun saveTokenToDataStore(token: String) {
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }
    val token: Flow<String>
        get() = context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.d("TOKEN: ", "EMPTY")
                    emit(emptyPreferences())
                } else {
                    exception.message?.let { Log.d("ERROR TOKEN: ", it) }

                }
            }.map { preferences ->
                Log.d("TOKEN: ", "Mapeando token")
                preferences[TOKEN] ?: ""
            }
}