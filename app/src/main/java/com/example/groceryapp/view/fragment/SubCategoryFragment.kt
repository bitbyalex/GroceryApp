package com.example.groceryapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.groceryapp.MyApplication
import com.example.groceryapp.R
import com.example.groceryapp.databinding.FragmentSubCategoryBinding
import com.example.groceryapp.model.data.remote.Data
import com.example.groceryapp.utils.CommonViewModelFactory
import com.example.groceryapp.view.adapter.CategoryAdapter
import com.example.groceryapp.view.adapter.subadapters.SubCategoryBeefAdapter
import com.example.groceryapp.view.adapter.subadapters.SubCategoryChickenAdapter
import com.example.groceryapp.view.adapter.subadapters.SubCategoryFruitsAdapter
import com.example.groceryapp.view.adapter.subadapters.SubCategoryVeggiesAdapter
import com.example.groceryapp.viewmodel.CategoryViewModel

class SubCategoryFragment : Fragment(), CategoryAdapter.Listener {

    private var _binding : FragmentSubCategoryBinding? = null
    private val binding get() = _binding!!
    private val categoryViewModel: CategoryViewModel by viewModels(factoryProducer = {
        CommonViewModelFactory(
            activity?.application as MyApplication
        )
    })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = arguments?.getString("category_id")
        Log.e("SubCategory", "Category id: $categoryId")


        //categoryViewModel = ViewModelProvider(this, CommonViewModelFactory())[CategoryViewModel::class.java]
        categoryViewModel.getSubCategories(categoryId.toString())
        categoryViewModel.subCategoryList.observe(viewLifecycleOwner) {
            Log.e("SubCategory", it.joinToString())
            if (categoryId != null) {
                when (categoryId.toInt()) {
                    1 -> {
                        val fm = SubCategoryChickenAdapter(childFragmentManager, it.size)
                        childFragmentManager.beginTransaction()
                        binding.viewPager.adapter = fm
                        binding.tabLayout.setupWithViewPager(binding.viewPager)
                        for (i in it.indices) {
                            binding.tabLayout.getTabAt(i)?.text = it[i].subName
                        }
                    }
                    2 -> {
                        val fm = SubCategoryVeggiesAdapter(childFragmentManager, it.size)
                        childFragmentManager.beginTransaction()
                        binding.viewPager.adapter = fm
                        binding.tabLayout.setupWithViewPager(binding.viewPager)
                        for (i in it.indices) {
                            binding.tabLayout.getTabAt(i)?.text = it[i].subName
                        }
                    }
                    3 -> {
                        val fm = SubCategoryFruitsAdapter(childFragmentManager, it.size)
                        childFragmentManager.beginTransaction()
                        binding.viewPager.adapter = fm
                        binding.tabLayout.setupWithViewPager(binding.viewPager)
                        for (i in it.indices) {
                            binding.tabLayout.getTabAt(i)?.text = it[i].subName
                        }
                    }
                    4 -> {
                        val fm = SubCategoryBeefAdapter(childFragmentManager, it.size)
                        childFragmentManager.beginTransaction()
                        binding.viewPager.adapter = fm
                        binding.tabLayout.setupWithViewPager(binding.viewPager)
                        for (i in it.indices) {
                            binding.tabLayout.getTabAt(i)?.text = it[i].subName
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(category: Data) {
        val bundle = Bundle().apply {
            putString("category_id", category._id)
        }
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // Pass subcategory Fragment
        transaction.replace(R.id.fragment_container, SubCategoryFragment().apply {
            arguments = bundle
        })
        transaction.addToBackStack(null)
        transaction.commit()
    }
}