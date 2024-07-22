package com.example.sharedprefslearning

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PreferenceManager(context: Context) {
    private val prefName = "MyAppPref"
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val pref = EncryptedSharedPreferences.create(
        prefName,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val editor = pref.edit()

    fun saveNewNumber(number: String) {
        editor.putString("number", number).apply()
    }

    fun getNumber(): String? {
        return pref.getString("number", "0")
    }
}