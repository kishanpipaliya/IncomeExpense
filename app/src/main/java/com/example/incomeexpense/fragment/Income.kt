package com.example.incomeexpense.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpense.adapter.CategoryAdapter
import com.example.incomeexpense.database.MyDataBase
import com.example.incomeexpense.R

class Income : Fragment() {


    lateinit var RcvList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_income, container, false)

        RcvList = v.findViewById(R.id.RcvList)

        initView()
        return v
    }

    private fun initView() {

        var list = MyDataBase(requireActivity()).Display()
        var adapter = CategoryAdapter(list)
        var Manager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        RcvList.layoutManager = Manager
        RcvList.adapter = adapter
    }


}