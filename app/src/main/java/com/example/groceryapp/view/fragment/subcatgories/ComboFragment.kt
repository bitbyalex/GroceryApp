package com.example.groceryapp.view.fragment.subcatgories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.databinding.FragmentComboBinding
import com.example.groceryapp.databinding.FragmentFriedBinding
import com.example.groceryapp.model.data.remote.Product
import com.example.groceryapp.utils.CommonViewModelFactory
import com.example.groceryapp.view.adapter.SubCategoryAdapter
import com.example.groceryapp.viewmodel.CategoryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComboFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComboFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var binding: FragmentComboBinding
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentComboBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel = ViewModelProvider(this, CommonViewModelFactory())[CategoryViewModel::class.java]
        categoryViewModel.getProductForSubCategory("3")
        categoryViewModel.itemsList.observe(viewLifecycleOwner) {
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