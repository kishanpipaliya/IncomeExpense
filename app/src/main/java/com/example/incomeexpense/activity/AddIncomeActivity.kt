package com.example.incomeexpense.activity

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpense.R
import com.example.incomeexpense.adapter.CategoryAddAdapter
import com.example.incomeexpense.adapter.PaymentTypeAdapter
import com.example.incomeexpense.database.IncomeExpenseDatabase
import com.example.incomeexpense.databinding.ActivityAddIncomeBinding
import com.example.incomeexpense.moelclass.PayListClass
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*
import kotlin.math.max


class AddIncomeActivity : AppCompatActivity() {

    lateinit var TypeIE: String
    lateinit var binding: ActivityAddIncomeBinding
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    var maxLength = 9223372036854775807


    var c: Calendar = Calendar.getInstance()
    var year: Int = c.get(Calendar.YEAR)
    var month: Int = c.get(Calendar.MONTH)
    var day: Int = c.get(Calendar.DAY_OF_MONTH)

    var Category = arrayOf(
        "Auto Rickshaw",
        "Bike",
        "Bills",
        "Tv",
        "Car",
        "Clothes",
        "Education",
        "Electricity",
        "Entertainment",
        "Food",
        "Fuel",
        "Groceries",
        "Health",
        "Internet",
        "Investment",
        "Shopping",
        "Taxi",
        "Toll",
        "Transport"
    )
    var imgAll = arrayOf(
        R.drawable.ic_rickshaw,
        R.drawable.ic_bike,
        R.drawable.ic_bills,
        R.drawable.ic_tv,
        R.drawable.ic_car,
        R.drawable.ic_clothes,
        R.drawable.ic_education,
        R.drawable.ic_electricity,
        R.drawable.ic_entertainment,
        R.drawable.ic_food,
        R.drawable.ic_fuel,
        R.drawable.ic_groceries,
        R.drawable.ic_health,
        R.drawable.ic_internet,
        R.drawable.ic_investmentincome,
        R.drawable.ic_shoping,
        R.drawable.ic_taxi, R.drawable.ic_toll, R.drawable.ic_transport
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView() {


        TypeIE = intent.getStringExtra("Type").toString()


        val time = Calendar.getInstance()
        var hour = time.get(Calendar.HOUR)
        var minutes = time.get(Calendar.MINUTE)
        var timeSet = ""
        if (hour > 12) {
            hour -= 12
            timeSet = "PM"
        } else if (hour === 0) {
            hour += 12
            timeSet = "AM"
        } else if (hour === 12) {
            timeSet = "PM"
        } else {
            timeSet = "AM"
        }
        var times = hour.toString() + ":" + minutes.toString() + " " + timeSet

        binding.txtTime.setText(times)
        binding.txtAddIncomeTitle.setText("Add " + TypeIE)
        binding.entIncome.setText(TypeIE)
        binding.txtOther.text = ("Other " + TypeIE)
        if (TypeIE == "Income") {
            binding.entIncome.setTextColor(ContextCompat.getColor(this, R.color.Green))
        } else {
            binding.entIncome.setTextColor(ContextCompat.getColor(this, R.color.red))
        }



        binding.btnPayment.setOnClickListener {
            var PayList = PayListClass().run()
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.payment)


            var rcl = dialog.findViewById<RecyclerView>(R.id.rclPay)


            val adapter = PaymentTypeAdapter(PayList) { PayType ->
                binding.txtPayment.setText(PayType)
                dialog.dismiss()
            }
            var layoutManager1 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rcl?.layoutManager = layoutManager1
            rcl?.adapter = adapter
            dialog.show()
        }



        binding.btnBackArrow.setOnClickListener {
            finish()
        }


        binding.btnSave.setOnClickListener {
            var income = java.lang.Long.valueOf(binding.edtIncome.text.toString())
            Log.e("===============", "initView: $income" )
            var other = binding.txtOther.text.toString()
            var method = binding.txtPayment.text.toString()
            var note = binding.edtNotes.text.toString()
            var date = binding.txtDate.text.toString()
            var time = binding.txtTime.text.toString()



            binding.entIncome.filters += InputFilter.LengthFilter(maxLength.toInt())

            if (binding.edtIncome.text.isEmpty()) {
                binding.edtIncome.setError("Enter Income")
            }
            else if (other.isEmpty()) {
                binding.txtOther.setError("Enter Category")
            } else if (method.isEmpty()) {
                binding.txtPayment.setError("Enter Method")
            } else {
                var type = 1
                if (TypeIE.equals("Income")) {
                    type = 1
                } else {
                    type = 0
                }
                Toast.makeText(this, "Transaction Added", Toast.LENGTH_SHORT).show()
                var database = IncomeExpenseDatabase(this)
                database.insertData(income, other, method, note, date, time, type)

                notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val intent = Intent(this, MainActivity::class.java)
                val pendingIntent =
                    PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                // RemoteViews are used to use the content of
                // some different layout apart from the current activity layout
//                val contentView = RemoteViews(packageName, R.layout.activity_after_notification)
                // checking if android version is greater than oreo(API 26) or not
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(
                        channelId,
                        description,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this, channelId)
                        .setContentTitle(TypeIE)
                        .setSmallIcon(R.drawable.ic_inexlogo)
                        .setContentText("Add Transaction SuccessFully")
//                        .setContent(contentView)
                        .setColor(ContextCompat.getColor(this, R.color.blue))
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.ic_inexlogo
                            )
                        )
                        .setContentIntent(pendingIntent)
                } else {
                    builder = Notification.Builder(this)
                        .setContentTitle(TypeIE)
                        .setSmallIcon(R.drawable.ic_inexlogo)
                        .setColor(ContextCompat.getColor(this, R.color.blue))
                        .setContentText("Add Transaction SuccessFully")
//                        .setContent(contentView)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.ic_inexlogo
                            )
                        )
                        .setContentIntent(pendingIntent)
                }
                notificationManager.notify(1234, builder.build())
                finish()
            }
        }


        binding.txtDate.text = (day.toString() + "-" + (month + 1) + "-" + year)
        binding.imgLeftClick.setOnClickListener {
            c.add(Calendar.DAY_OF_MONTH, -1)
            c.add(Calendar.MONTH, 0)
            c.add(Calendar.YEAR, 0)
            val currentDatePlusOne = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)
            binding.txtDate.text = (currentDatePlusOne.toString() + "-" + (month + 1) + "-" + year)

        }
        binding.imgRightClick.setOnClickListener {

            val currentTime = Calendar.getInstance()
            if (c.get(Calendar.DAY_OF_MONTH).equals(currentTime[Calendar.DAY_OF_MONTH]) && c.get(
                    Calendar.MONTH
                ).equals(currentTime[Calendar.MONTH]) && c.get(Calendar.YEAR)
                    .equals(currentTime[Calendar.YEAR])
            ) {
            } else {
                c.add(Calendar.DAY_OF_MONTH, +1)
                c.add(Calendar.MONTH, 0)
                c.add(Calendar.YEAR, 0)
                val currentDatePlusOne = c.get(Calendar.DAY_OF_MONTH)
                val month = c.get(Calendar.MONTH)
                val year = c.get(Calendar.YEAR)
                binding.txtDate.text =
                    (currentDatePlusOne.toString() + "-" + (month + 1) + "-" + year)
            }

        }
        binding.imgDate.setOnClickListener {

            day = c.get(Calendar.DAY_OF_MONTH)
            year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)


            var datePickerDialog = DatePickerDialog(
                this, { view, year, monthOfYear, dayOfMonth ->
                    this.year = year
                    this.month = monthOfYear
                    this.day = dayOfMonth
                    binding.txtDate.text =
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                },
                year,
                month,
                day
            )
//             Max Date
           datePickerDialog.getDatePicker().setMaxDate(Date().time)

            datePickerDialog.show()
        }




        binding.imgTime.setOnClickListener {
            val c = Calendar.getInstance()
            var hour = c.get(Calendar.HOUR)
            var minutes = c.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    hour = hourOfDay
                    minutes = minute
                    var timeSet = ""
                    if (hour > 12) {
                        hour -= 12
                        timeSet = "PM"
                    } else if (hour === 0) {
                        hour += 12
                        timeSet = "AM"
                    } else if (hour === 12) {
                        timeSet = "PM"
                    } else {
                        timeSet = "AM"
                    }
                    var time = hour.toString() + ":" + minute.toString() + " " + timeSet
                    binding.txtTime.setText(time)
                },
                hour,
                minutes,
                false
            )
            timePickerDialog.show()
        }



        binding.btnClickCategory.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.grid_view_item)
            var AddGridView = bottomSheetDialog.findViewById<GridView>(R.id.AddGridView)!!


            var v = LayoutInflater.from(this).inflate(R.layout.grid_view, null)

//            var imgCateGory = v.findViewById<ImageView>(R.id.BtnCateClick)
            var txtCategoryName = v.findViewById<TextView>(R.id.txtCategoryName)

            var categoryAddAdapter = CategoryAddAdapter(this, Category, imgAll) { Image, cate ->
                binding.imgCate.setImageResource(Image)
                binding.txtOther.setText(cate).toString()
                bottomSheetDialog.dismiss()
            }

            AddGridView.adapter = categoryAddAdapter
            bottomSheetDialog.show()

        }
        binding.BtnBottomPayment.setOnClickListener {
            var PayList = PayListClass().run()
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.payment)
            var rcl = dialog.findViewById<RecyclerView>(R.id.rclPay)
            val adapter = PaymentTypeAdapter(PayList) { PayType ->
                binding.txtPayment.setText(PayType)
                dialog.dismiss()
            }
            var layoutManager1 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rcl?.layoutManager = layoutManager1
            rcl?.adapter = adapter
            dialog.show()
        }
        binding.btnBottomCategory.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.grid_view_item)
            var AddGridView = bottomSheetDialog.findViewById<GridView>(R.id.AddGridView)!!


            var v = LayoutInflater.from(this).inflate(R.layout.grid_view, null)

//            var imgCateGory = v.findViewById<ImageView>(R.id.BtnCateClick)
            var txtCategoryName = v.findViewById<TextView>(R.id.txtCategoryName)

            var categoryAddAdapter = CategoryAddAdapter(this, Category, imgAll) { Image, cate ->
                binding.imgCate.setImageResource(Image)
                binding.txtOther.setText(cate).toString()
                bottomSheetDialog.dismiss()
            }

            AddGridView.adapter = categoryAddAdapter
            bottomSheetDialog.show()
        }
    }


}
