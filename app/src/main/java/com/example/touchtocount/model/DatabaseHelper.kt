package com.example.touchtocount.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "cntDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE countTable (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "count INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS countTable")
        onCreate(db)
    }

    fun insertCount(count: Int) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("COLUMN_COUNT", count)
        db.insert("countTable", null, values)
        db.close()
    }

}