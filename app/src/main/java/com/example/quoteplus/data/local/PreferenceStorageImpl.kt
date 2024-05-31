package com.example.quoteplus.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import com.example.quoteplus.domain.PreferenceStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "quotes_preferences")

@Singleton
class PreferenceStorageImpl @Inject constructor(
    @ApplicationContext context: Context) :
    PreferenceStorage {

    private val dataStore = context.dataStore


    private object PreferencesKeys {
        val TOKEN = stringPreferencesKey("TOKEN")
    }

    override fun getToken(): Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[DataStoreManager.TOKEN] ?: ""
        }


    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[PreferencesKeys.TOKEN] = token
        }
    }

}