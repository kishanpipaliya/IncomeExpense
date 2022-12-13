package com.example.incomeexpense.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpense.R
import com.example.incomeexpense.moelclass.DatabnaseModelClass
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class TransactionAdapter(
    val context: Context,
    val transactionList: ArrayList<DatabnaseModelClass>
) : RecyclerView.Adapter<TransactionAdapter.MyViewHolder>() {
    var inc :Long?= 0
//    var datechecker = ArrayList<String>()
    var exp :Long?= 0
//    val transactions: ArrayList<DatabnaseModelClass>()
    var dateinc :Long?= 0
    var Stringdate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var txtIncome = v.findViewById<TextView>(R.id.txtIncome)
        var txtExpense = v.findViewById<TextView>(R.id.txtExpense)
        var txtDate = v.findViewById<TextView>(R.id.txtDate)
        var txtSaving = v.findViewById<TextView>(R.id.txtSaving)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.transaction, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var format: DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        val date: Date = format.parse(Stringdate)
        Log.e(
            TAG,
            "onBindViewHolder: " + Stringdate + "=====" + date + "======" + transactionList.get(
                position
            ).Date.toString()
        )


        holder.txtDate.text = transactionList.get(position).Date.toString()

//        if (Stringdate.equals(transactionList.get(position).Date.toString())) {
            if (transactionList.get(position).Type == 1) {
                holder.txtDate.setText(transactionList.get(position).Date.toString())
                holder.txtIncome.setText(transactionList.get(position).IncomeExpense.toString())
                holder.txtIncome.setTextColor(ContextCompat.getColor(context, R.color.Green))
                inc = inc?.plus(transactionList.get(position).IncomeExpense)
                Log.e("TAG", "onBindViewHolder: " + transactionList.get(position).IncomeExpense)
            } else {
                holder.txtDate.setText(transactionList.get(position).Date.toString())
                holder.txtExpense.setText(transactionList.get(position).IncomeExpense.toString())
                holder.txtExpense.setTextColor(ContextCompat.getColor(context, R.color.red))
                exp = exp?.minus(transactionList.get(position).IncomeExpense)
            }
        var saving= inc?.plus(exp!!)
        if (saving != null) {
            if(saving<=0){
                holder.txtSaving.setText(saving.toString())
                holder.txtSaving.setTextColor(Color .RED)
            }
        }
        dateinc = dateinc?.plus(transactionList.get(position).IncomeExpense)


//        dateinc += (inc - exp)
//        if (position == (transactionList.size - 1)) {
//            data.invoke(inc, exp, dateinc)
//        }
    }

    override fun getItemCount(): Int {
//        datefixer()
        return transactionList.size
    }
}