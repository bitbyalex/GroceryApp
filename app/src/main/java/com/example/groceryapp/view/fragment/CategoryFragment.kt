package com.example.groceryapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.groceryapp.MyApplication
import com.example.groceryapp.R
import com.example.groceryapp.databinding.FragmentCategoryBinding
import com.example.groceryapp.model.data.remote.Data
import com.example.groceryapp.utils.CommonViewModelFactory
import com.example.groceryapp.view.adapter.CategoryAdapter
import com.example.groceryapp.viewmodel.CategoryViewModel

class CategoryFragment : Fragment(), CategoryAdapter.Listener{

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val categoryViewModel: CategoryViewModel by viewModels(
        factoryProducer = {
            CommonViewModelFactory(
                (activity?.application as MyApplication)
            )
        }
    )
    private lateinit var categoryAdapter: CategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //categoryViewModel = ViewModelProvider(this, CommonViewModelFactory())[CategoryViewModel::class.java]
        categoryAdapter = CategoryAdapter(emptyList())
        categoryAdapter.listener = this

        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoryAdapter
        }

        categoryViewModel.categoryList.observe(viewLifecycleOwner) { categoryList ->
            categoryAdapter.setCategories(categoryList)
        }

        categoryViewModel.getCategories()

        binding.btnSearch.setOnClickListener{
            val searchFragment = SearchFragment.newInstance(binding.edtSearch.text.toString())
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,searchFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(category: Data) {
        Log.e("SubCategory", "On click, ${category.catId}")
        val bundle = Bundle().apply {
            putString("category_id", category.catId.toString())
        }
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, SubCategoryFragment().apply {
            arguments = bundle
        })
        transaction.addToBackStack(null)
        transaction.commit()
    }
}