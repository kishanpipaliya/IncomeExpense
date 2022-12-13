package com.example.incomeexpense.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.incomeexpense.moelclass.MyModelClass

class MyDataBase(var context: Context) : SQLiteOpenHelper(context, "MyCategory", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        var sql =
            "create table Category(CategoryId integer primary key autoincrement,CategoryName text,CateImage integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun Insert(edit: String) {

        var sql = writableDatabase
        var data = ContentValues()
        data.put("CategoryName", edit)
        val result = sql.insert("Category", null, data)
        Log.e("TAG", "Insert: " + result)
    }

    fun Display(): ArrayList<MyModelClass> {

        var list: ArrayList<MyModelClass> = ArrayList()
        var db = readableDatabase
        var sql = "select * from Category"
        var cursor = db.rawQuery(sql, null)

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {
                do {

                    var id = cursor.getInt(0)
                    var Category = cursor.getString(1)
                    var model = MyModelClass(id, Category)
                    list.add(model)
                } while (cursor.moveToNext())
                return list
            }
        } else {

        }
        return list

    }
}