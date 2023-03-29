package com.example.groceryapp.view.fragment.subcatgories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.databinding.FragmentBeefComboBinding
import com.example.groceryapp.databinding.FragmentBeefKabobBinding
import com.example.groceryapp.model.data.remote.Product
import com.example.groceryapp.utils.CommonViewModelFactory
import com.example.groceryapp.view.adapter.SubCategoryAdapter
import com.example.groceryapp.viewmodel.CategoryViewModel

class BeefKabobFragment : Fragment() {

    private lateinit var binding: FragmentBeefKabobBinding
    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeefKabobBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this, CommonViewModelFactory())[CategoryViewModel::class.java]
        viewModel.getProductForSubCategory("9")
        viewModel.itemsList.observe(viewLifecycleOwner) {
            val list: MutableList<Product> = mutableListOf()
            for (i in it.indices) {
                list.add(it[i])
            }
            val adapter = SubCategoryAdapter(list)
            binding.rvItems.layoutManager = LinearLayoutManager(requireContext())
            binding.rvItems.adapter = adapter
        }
    }
}