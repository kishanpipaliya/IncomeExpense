package com.example.incomeexpense.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpense.R

class PaymentTypeAdapter (val PayList: ArrayList<String>, var invkPayType:((String)->Unit)) :
    RecyclerView.Adapter<PaymentTypeAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtPayTypes = itemView.findViewById<TextView>(R.id.txtPayTypes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.payment_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtPayTypes.setText(PayList.get(position).toString())
        holder.txtPayTypes.setOnClickListener{
            invkPayType.invoke(PayList.get(position).toString())
        }
    }

    override fun getItemCount(): Int {
        return PayList.size
    }
}