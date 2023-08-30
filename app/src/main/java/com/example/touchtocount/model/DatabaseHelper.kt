package com.example.touchtocount.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "cntDB", null, 2) {
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
        values.put("count", count) // "count" 컬럼에 카운트 값을 넣습니다.
        db.insert("countTable", null, values)
        db.close()
        Log.d("Database", "Inserted count: $count into countTable")
    }

    @SuppressLint("Range")
    fun getCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT count FROM countTable ORDER BY id DESC LIMIT 1", null)
        var count = 0

        if (cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndex("count"))
        }

        cursor.close()
        db.close()
        return count
    }
}
