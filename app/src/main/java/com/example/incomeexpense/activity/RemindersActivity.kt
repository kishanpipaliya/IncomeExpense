package com.example.incomeexpense.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.incomeexpense.R
import com.example.incomeexpense.adapter.ReminderAdapter
import com.google.android.material.tabs.TabLayout

class RemindersActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)

        initView()
    }

    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById (R.id.viewPager)


        tabLayout?.addTab(tabLayout.newTab().setText("TODAY"))
        tabLayout?.addTab(tabLayout.newTab().setText("ALL"))


        var adapter = ReminderAdapter(supportFragmentManager, tabLayout.tabCount)
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
    }
}