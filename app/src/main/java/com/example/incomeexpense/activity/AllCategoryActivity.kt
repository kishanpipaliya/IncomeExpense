package com.example.incomeexpense.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.incomeexpense.R
import com.example.incomeexpense.TabAdapter
import com.google.android.material.tabs.TabLayout

class AllCategoryActivity() : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var btnAdd: ImageView
    lateinit var BtnAddNew : TextView
    lateinit var btnBackArrow : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_category)

        initView()
    }

    private fun initView() {
        btnAdd = findViewById(R.id.btnAdd)
        BtnAddNew = findViewById(R.id.BtnAddNew)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById (R.id.viewPager)
        btnBackArrow = findViewById (R.id.btnBackArrow)


        tabLayout?.addTab(tabLayout.newTab().setText("INCOME"))
        tabLayout?.addTab(tabLayout.newTab().setText("EXPENSE"))


        var adapter = TabAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        btnAdd.setOnClickListener {
            var intent = Intent(this, AddCategoryActivity::class.java)
            startActivity(intent)
        }
        BtnAddNew.setOnClickListener {
            var intent = Intent(this, AddCategoryActivity::class.java)
            startActivity(intent)
        }
        btnBackArrow.setOnClickListener {
            finish()
        }


    }
}