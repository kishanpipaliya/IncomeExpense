package com.example.incomeexpense.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.incomeexpense.database.MyDataBase
import com.example.incomeexpense.R


class AddCategoryActivity : AppCompatActivity() {

    lateinit var edtAddCategory: EditText
    lateinit var imgClick: ImageView
    lateinit var btnClick: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_categort)


        initView()
    }

    private fun initView() {
        edtAddCategory = findViewById(R.id.edtAddCategory)
        imgClick = findViewById(R.id.imgClick)
        btnClick = findViewById(R.id.btnClick)
        var myDataBase = MyDataBase(this)

        imgClick.setOnClickListener {

            var edit = edtAddCategory.text.toString()
            if (edit.isEmpty()) {
                edtAddCategory.setError("Add Category")
            } else {
                myDataBase.Insert(edit)
                Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        btnClick.setOnClickListener {
            var edit = edtAddCategory.text.toString()
            if (edit.isEmpty()) {
                edtAddCategory.setError("Add Category")
            } else {
                myDataBase.Insert(edit)
                Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show()
            }
        }


    }
}