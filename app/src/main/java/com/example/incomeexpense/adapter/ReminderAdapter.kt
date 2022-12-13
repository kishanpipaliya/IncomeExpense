package com.example.incomeexpense.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.incomeexpense.fragment.ReminderALL
import com.example.incomeexpense.fragment.ReminderToday

class ReminderAdapter(
    supportFragmentManager: FragmentManager, var tabCount: Int
) : FragmentPagerAdapter(supportFragmentManager, tabCount) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {

        if (position == 0) {
            return ReminderToday()
        } else if (position == 1) {
            return ReminderALL()
        } else {
            return ReminderToday()
        }


    }

}