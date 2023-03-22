package com.example.groceryapp.view.adapter.subadapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.groceryapp.view.fragment.subcatgories.*

class SubCategoryFruitsAdapter(private var fm: FragmentManager, private val count: Int) :
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int = count

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0-> FruitsFragment()
            1 -> VegetablesFragment()
            else -> VegetablesFragment()
        }
    }
}

