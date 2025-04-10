package com.example.writeimgtodb.Db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.writeimgtodb.models.RecyItemModel

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, "dataname.db", null, 1) {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val URI_NAME = "uri_name"
        const val IMAGE_TABLE = "image_table"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $IMAGE_TABLE (
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $NAME TEXT NOT NULL,
                $URI_NAME TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $IMAGE_TABLE")
        onCreate(db)
    }

    fun addItem(model: RecyItemModel) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(NAME, model.name)
            put(URI_NAME, model.uriNme)
        }
        db.insert(IMAGE_TABLE, null, values)
        db.close()
    }

    fun getItem(): List<RecyItemModel> {
        val list = ArrayList<RecyItemModel>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $IMAGE_TABLE", null)
        if (cursor.moveToFirst()) {
            do {
                val model = RecyItemModel(
                    id = cursor.getInt(0),
                    name = cursor.getString(1),
                    uriNme = cursor.getString(2)
                )
                list.add(model)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}
