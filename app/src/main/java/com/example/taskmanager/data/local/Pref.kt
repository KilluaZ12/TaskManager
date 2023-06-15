package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(private val context: Context) {

    private val pref by lazy {
        context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    fun isUserSeen(): Boolean {
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun saveSeen() {
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    fun saveName(name: String) {
        pref.edit().putString(SAVE_NAME, name).apply()
    }

    fun saveAvatar(uri: String) {
        pref.edit().putString(SAVE_AVATAR, uri).apply()
    }

    fun getAvatar(): String? {
        return pref.getString(SAVE_AVATAR, null)
    }

    fun getName(): String? {
       return pref.getString(SAVE_NAME, "")
    }

    companion object {
        const val PREF_NAME = "task.pref"
        const val SEEN_KEY = "user_key"
        const val SAVE_NAME = "user_name"
        const val SAVE_AVATAR = "user_avatar"
    }

}