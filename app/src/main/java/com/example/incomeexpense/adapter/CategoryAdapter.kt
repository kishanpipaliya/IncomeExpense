package com.example.incomeexpense.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpense.moelclass.MyModelClass
import com.example.incomeexpense.R

class CategoryAdapter(var list: ArrayList<MyModelClass>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtName=view.findViewById<TextView>(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.show_category,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.txtName.setText(list.get(position).Category)
    }

    override fun getItemCount(): Int {
       return list.size
    }
}


