package com.example.incomeexpense.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.incomeexpense.R

class ReminderToday : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var v= inflater.inflate(R.layout.fragment_reminder_today, container, false)
        initView()
        return v
    }

    private fun initView() {

    }


}