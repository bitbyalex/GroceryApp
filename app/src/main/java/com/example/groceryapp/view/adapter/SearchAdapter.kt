package com.example.groceryapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.groceryapp.databinding.SearchItemBinding
import com.example.groceryapp.model.Constants
import com.example.groceryapp.model.data.remote.Product
import com.squareup.picasso.Picasso

class SearchAdapter(private val searchList: List<Product>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private lateinit var binding : SearchItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = SearchItemBinding.inflate(layoutInflater,parent,false)
        return SearchViewHolder(binding.root)
    }

    override fun getItemCount(): Int = searchList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            val searchItem = searchList[position]
            bind(searchItem)
        }
    }

    inner class SearchViewHolder(view: View) : ViewHolder(view){
        fun bind(searchItem : Product) {
            binding.apply {
                txtDescription.text = searchItem.description
                txtPrice.text = "$ %.2f".format(searchItem.price)
                txtItemName.text = searchItem.productName
                val url = Constants.BASE_IMAGE_URL + searchItem.image
                try {
                    Picasso.get().load(url).into(binding.imgItem)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

}