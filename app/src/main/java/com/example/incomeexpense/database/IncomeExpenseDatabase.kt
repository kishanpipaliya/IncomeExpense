package com.example.incomeexpense.database

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.incomeexpense.activity.SummaryActivity
import com.example.incomeexpense.moelclass.DatabnaseModelClass

class IncomeExpenseDatabase(var context: Context) :

    SQLiteOpenHelper(context, "MyInEx.db", null, 1) {
    var modelArray = ArrayList<DatabnaseModelClass>()


    override fun onCreate(db: SQLiteDatabase?) {
        var sql =
            "create table IncomeExpense(IncomeId integer primary key autoincrement,IncomeExpense integer,Category text,PaymentMethod text,Notes text,Type integer,Date text,Time integer)"
        db?.execSQL(sql)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(
        income: Long,
        other: String,
        method: String,
        note: String,
        date: String,
        time: String,
        type: Int
    ) {
        var sql = writableDatabase
        var c = ContentValues()
        c.put("IncomeExpense", income)
        c.put("Category", other)
        c.put("PaymentMethod", method)
        c.put("Notes", note)
        c.put("Date", date)
        c.put("Time", time)
        c.put("Type", type)

        var result = sql.insert("IncomeExpense", null, c)
        Log.e(TAG, "insertData: " + result)
    }

    fun DisplayDatas(): ArrayList<DatabnaseModelClass> {

        var totalin :Long?= 0
        var totalex : Long?= 0
        var sql = readableDatabase
        var sqldata = "select * from IncomeExpense "
        var cursor = sql.rawQuery(sqldata, null)

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {
                do {
                    var IncomeId = cursor.getInt(0)
                    var IncomeExpense = cursor.getLong(1)
                    var Category = cursor.getString(2)
                    var PaymentMethod = cursor.getString(3)
                    var Notes = cursor.getString(4)
                    var Type = cursor.getInt(5)
                    var Date = cursor.getString(6)
                    var Time = cursor.getInt(7)

                    var modelclass = DatabnaseModelClass(
                        IncomeId,
                        IncomeExpense,
                        Category,
                        PaymentMethod,
                        Notes,
                        Type,
                        Date,
                        Time
                    )
                    if (Type == 1) {
                        totalin = totalin?.plus(IncomeExpense)

                        var intent = Intent(context, SummaryActivity::class.java)
                        intent.putExtra("income", totalin)
                        Log.e(TAG, "DisplayDatas=========: " + totalin)
                    } else {
                        totalex = totalex?.minus(IncomeExpense)
                        var intent = Intent(context, SummaryActivity::class.java)
                        intent.putExtra("expense", totalex)
                        Log.e(TAG, "DisplayDatas: " + totalex)
                    }
                    modelArray.add(modelclass)
                } while (cursor.moveToNext())

            }
            return modelArray
        } else {
            return modelArray
        }
    }
}