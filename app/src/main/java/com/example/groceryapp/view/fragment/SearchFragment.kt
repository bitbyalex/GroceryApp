package com.example.groceryapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.databinding.FragmentSearchBinding
import com.example.groceryapp.utils.CommonViewModelFactory
import com.example.groceryapp.view.adapter.SearchAdapter
import com.example.groceryapp.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private lateinit var viewModel : SearchViewModel

    private var searchText : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchText = arguments?.let {
            it.getString(SEARCH_TEXT)
        } ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, CommonViewModelFactory())[SearchViewModel::class.java]

        viewModel.searchProduct(searchText)

        viewModel.searchResults.observe(viewLifecycleOwner){
            val adapter = SearchAdapter(it)
            binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())
            binding.rvSearch.adapter = adapter
        }
    }

    companion object {
        const val SEARCH_TEXT = "SEARCH_TEXT"

        fun newInstance(searchText: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(SEARCH_TEXT, searchText)
                }
            }
    }
}