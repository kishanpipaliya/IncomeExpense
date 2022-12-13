package com.example.incomeexpense.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.incomeexpense.R
import com.example.incomeexpense.database.IncomeExpenseDatabase
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet


class CategoryChartActivity : AppCompatActivity() {


    lateinit var pieChart: PieChart
    var inc: Long? = 0
    var exp: Long? = 0
    var dateinc: Long? = 0
    var incexp: Long? = 0
    lateinit var txtTotalIncome: TextView
    lateinit var txtTotalExpense: TextView
    lateinit var txtTotalSaving: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_chart)

        pieChart = findViewById(R.id.pieChart)

        initView()

    }


    private fun initView() {
        txtTotalIncome = findViewById(R.id.txtTotalIncome_)
        txtTotalExpense = findViewById(R.id.txtTotalExpense_)
        txtTotalSaving = findViewById(R.id.txtTotalSaving_)


        var transactionList = IncomeExpenseDatabase(this).DisplayDatas()
        for (position in 0 until transactionList.size) {
            if (transactionList.get(position).Type == 1) {
                inc = inc?.plus(transactionList.get(position).IncomeExpense)
            } else {
                exp = exp?.minus(transactionList.get(position).IncomeExpense)
            }
        }

        dateinc = dateinc?.plus((inc!!.plus(exp!!)))

        txtTotalIncome.setText(inc.toString())
        txtTotalExpense.setText(exp.toString())
        txtTotalSaving.setText(dateinc.toString())

        pieChart.setDescription("")
        pieChart.centerText = "Balance\nPercentage"
        pieChart.setCenterTextSize(16f)
        pieChart.setBackgroundColor(resources.getColor(R.color.white))
        pieChart.holeRadius = 55f
        pieChart.setHoleColor(resources.getColor(R.color.white))
        var legend: Legend = pieChart.legend
        legend.position = Legend.LegendPosition.BELOW_CHART_RIGHT
        //colors
        legend.textColor = resources.getColor(R.color.black)
        legend.textSize = 14f


        var chartValue = ArrayList<String>()
        chartValue.add("income")
        chartValue.add("expense")


        var pieEntry = ArrayList<Entry>()
        incexp = incexp?.plus((inc!! - exp!!))


        var income = (incexp?.plus(exp!!))
        var expense = (incexp?.minus(inc!!))

        if (income != null) {
            pieEntry.add(Entry(income.toFloat() , 0))
        }
        if (expense != null) {
            pieEntry.add(Entry(expense.toFloat(), 1))
        }

        var colors = ArrayList<Int>()
        colors.add(Color.GREEN)
        colors.add(Color.RED)

        var pieDataSet = PieDataSet(pieEntry, "")

        // percentage icon and
        // given float number
//        pieDataSet.setValueFormatter(PercentFormatter())

        // color
        //   pieDataSet.color = resources.getColor(R.color.Green)
        pieDataSet.colors = colors
        var data = PieData(chartValue, pieDataSet)

        //inChartValue
        pieDataSet.valueTextSize = 14f

        // animation
        pieChart.animateXY(2500, 2500)
        pieChart.setUsePercentValues(true)


        //Chart Entry color and details name position set
        pieChart.data = data

    }
}