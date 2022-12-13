package com.example.incomeexpense.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpense.R
import com.example.incomeexpense.adapter.PaymentTypeAdapter
import com.example.incomeexpense.moelclass.PayListClass
import com.google.android.material.bottomsheet.BottomSheetDialog

class PaymentMethodActivity : AppCompatActivity() {

    lateinit var btnNew : TextView
//    lateinit var btnNewADD : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        initView()
    }

    private fun initView() {
        btnNew = findViewById(R.id.btnNew)
//        btnNewADD = findViewById(R.id.btnNewADD)

        btnNew.setOnClickListener {
            val dialog = Dialog(this@PaymentMethodActivity)
            dialog.setContentView(R.layout.delete)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            dialog.setCancelable(false)

            val btnCancel = dialog.findViewById<View>(R.id.btnCancel)
//            var btnSave = dialog.findViewById<TextView>(R.id.btnSave)
//            var edtName = dialog.findViewById<EditText>(R.id.edtName)


//            btnSave.setOnClickListener {
//                var name = edtName.text.toString()
//                btnNewADD.setText(name)
//                dialog.dismiss()
//
//            }
            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}