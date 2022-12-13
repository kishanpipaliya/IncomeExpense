package com.example.incomeexpense.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.incomeexpense.R

class CategoryAddAdapter(
    var context: Context,
    var Category: Array<String>,
    var imgAll: Array<Int>
    ,var CateNum:((Int,String)->Unit)
) : BaseAdapter() {
    override fun getCount(): Int {

        return Category.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(i: Int, view: View?, position: ViewGroup?): View {

        var imgCateGory: ImageView
        var BtnCateClick: LinearLayout
        var txtCategoryName: TextView


        var v = LayoutInflater.from(context).inflate(R.layout.grid_view, null)

        imgCateGory = v.findViewById(R.id.imgCateGory)
        txtCategoryName = v.findViewById(R.id.txtCategoryName)
        BtnCateClick = v.findViewById(R.id.BtnCateClick)

        BtnCateClick.setOnClickListener {
            CateNum.invoke(imgAll.get(i),Category.get(i).toString())
        }

        imgCateGory.setImageResource(imgAll[i])
        txtCategoryName.setText(Category[i])



        return v

    }
}
