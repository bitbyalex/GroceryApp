package com.example.groceryapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.databinding.CategoryItemBinding
import com.example.groceryapp.model.data.remote.Data
import com.example.groceryapp.model.Constants.BASE_IMAGE_URL
import com.squareup.picasso.Picasso

class CategoryAdapter(private var categoryList: List<Data>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var listener: Listener?=null

    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Data) {
            binding.cardView.setOnClickListener {
                listener?.onClick(category)
            }
            binding.txtCategory.text = category.catName
            Picasso.get().load(BASE_IMAGE_URL+category.catImage).into(binding.imgCategory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount() = categoryList.size


    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(categories: List<Data>) {
        categoryList = categories
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(category: Data)
    }
}

