package com.example.incomeexpense

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.incomeexpense.fragment.Expense
import com.example.incomeexpense.fragment.Income

class TabAdapter(supportFragmentManager: FragmentManager, var tabCount: Int) :
    FragmentPagerAdapter(supportFragmentManager, tabCount) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {

        if (position==0)
        {
            return Income()
        }
        else if(position == 1)
        {
            return Expense()
        }
        else{
            return Income()
        }


    }


}