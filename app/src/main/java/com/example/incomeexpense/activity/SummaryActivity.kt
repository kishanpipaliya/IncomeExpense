package com.example.incomeexpense.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incomeexpense.adapter.TransactionAdapter
import com.example.incomeexpense.database.IncomeExpenseDatabase
import com.example.incomeexpense.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySummaryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        initView()
    }

    private fun initView() {

        var transactionList = IncomeExpenseDatabase(this).DisplayDatas()

        var TransAdapter = TransactionAdapter(this, transactionList)
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        binding.RcvList.layoutManager = manager
        binding.RcvList.adapter = TransAdapter
        var inc: Long? = 0
        var exp: Long? = 0
        var total: Long? = 0
        transactionList = IncomeExpenseDatabase(this).DisplayDatas()
        for (position in 0 until transactionList.size) {
            if (inc != null) {
                if (exp != null) {
                    if (transactionList.get(position).Type == 1) {
                        inc += transactionList.get(position).IncomeExpense
                    } else {
                        exp -= transactionList.get(position).IncomeExpense
                    }
                }
            }
        }

        total = total?.plus((inc!!.plus(exp!!)))

        binding.txtTotalIncome.setText(inc.toString())
        binding.txtTotalExpense.setText(exp.toString())
        binding.txtTotalSaving.setText(total.toString())

        if (total != null) {
            if (total <= 0) {
                binding.txtTotalSaving.setTextColor(Color.RED)
            }
        }
        binding.btnBackArrow.setOnClickListener {
            finish()
        }
    }


}

