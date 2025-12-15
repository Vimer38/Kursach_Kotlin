package com.example.kursah_kotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kursah_kotlin.data.local.dao.IngredientDao
import com.example.kursah_kotlin.data.local.dao.RecipeDao
import com.example.kursah_kotlin.data.local.dao.UserDao
import com.example.kursah_kotlin.data.local.entities.IngredientEntity
import com.example.kursah_kotlin.data.local.entities.RecipeEntity
import com.example.kursah_kotlin.data.local.entities.RecipeIngredientCrossRef
import com.example.kursah_kotlin.data.local.entities.UserEntity

@Database(
    entities = [
        IngredientEntity::class,
        RecipeEntity::class,
        RecipeIngredientCrossRef::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
    abstract fun recipeDao(): RecipeDao
    abstract fun userDao(): UserDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS users (
                        userId TEXT NOT NULL PRIMARY KEY,
                        email TEXT NOT NULL,
                        firstName TEXT,
                        lastName TEXT,
                        age TEXT,
                        goal TEXT,
                        photoPath TEXT,
                        nickname TEXT
                    )
                """.trimIndent())
            }
        }
    }
}

