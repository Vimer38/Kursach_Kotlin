package com.example.kursah_kotlin.data.local

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFS_NAME = "user_preferences"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_AGE = "age"
        private const val KEY_EMAIL = "email"
        private const val KEY_GOAL = "goal"
        private const val KEY_PHOTO_PATH = "photo_path"
    }

    fun saveFirstName(firstName: String) {
        prefs.edit().putString(KEY_FIRST_NAME, firstName).apply()
    }

    fun getFirstName(): String? {
        return prefs.getString(KEY_FIRST_NAME, null)
    }

    fun saveLastName(lastName: String) {
        prefs.edit().putString(KEY_LAST_NAME, lastName).apply()
    }

    fun getLastName(): String? {
        return prefs.getString(KEY_LAST_NAME, null)
    }

    fun saveAge(age: String) {
        prefs.edit().putString(KEY_AGE, age).apply()
    }

    fun getAge(): String? {
        return prefs.getString(KEY_AGE, null)
    }

    fun saveEmail(email: String) {
        prefs.edit().putString(KEY_EMAIL, email).apply()
    }

    fun getEmail(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }

    fun saveGoal(goal: String) {
        prefs.edit().putString(KEY_GOAL, goal).apply()
    }

    fun getGoal(): String? {
        return prefs.getString(KEY_GOAL, null)
    }

    fun savePhotoPath(photoPath: String) {
        prefs.edit().putString(KEY_PHOTO_PATH, photoPath).apply()
    }

    fun getPhotoPath(): String? {
        return prefs.getString(KEY_PHOTO_PATH, null)
    }

    fun clearAll() {
        prefs.edit().clear().apply()
    }
}

