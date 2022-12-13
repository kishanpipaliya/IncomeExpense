package com.example.incomeexpense.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.incomeexpense.R
import com.example.incomeexpense.activity.*

class MoreOptionAdapter(
    var context: Context,
    var moreImage: Array<Int>,
    var moreImageName: Array<String>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return moreImage.size
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, position: ViewGroup?): View {

        var btnClick: LinearLayout
        var imageView: ImageView
        var textview: TextView
        var v = LayoutInflater.from(context).inflate(R.layout.more_option, null)

        imageView = v.findViewById(R.id.imageView)
        textview = v.findViewById(R.id.textView)
        btnClick = v.findViewById(R.id.btnClick)

        imageView.setImageResource(moreImage.get(i))
        textview.setText(moreImageName.get(i))

        btnClick.setOnClickListener {
            var intent: Intent? = null


            when (i) {

                0 -> {
                    intent = Intent(context, SummaryActivity::class.java)
                }
                1 -> {
                    intent = Intent(context, AddCategoryActivity::class.java)
                }
                2 -> {
                    intent = Intent(context, CategoryChartActivity::class.java)
                }
                3 -> {
                    intent = Intent(context, BudgetActivity::class.java)
                }
                4 -> {
                    intent = Intent(context, PaymentMethodActivity::class.java)
                }
                5 -> {
//                    intent = Intent(context, PaymentMethodChartActivity::class.java)
                    return@setOnClickListener
                }

                6 -> {
//                    intent = Intent(context, TransactionAllAccountsActivity::class.java)
                    return@setOnClickListener
                }
                7 -> {
                    intent = Intent(context, RemindersActivity::class.java)
                }
                8 -> {

//                    intent = Intent(context, AccountActivity::class.java)
                    return@setOnClickListener
                }


            }

            context.startActivity(intent)
        }

        return v
    }
}