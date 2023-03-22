package com.example.groceryapp.view.adapter.subadapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.groceryapp.view.fragment.subcatgories.VegComboFragment
import com.example.groceryapp.view.fragment.subcatgories.VegFriedFragment

class SubCategoryVeggiesAdapter(fm: FragmentManager, private val subCount:Int) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return subCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0-> VegFriedFragment()
            1 -> VegComboFragment()
            else -> VegComboFragment()
        }

    }
}

