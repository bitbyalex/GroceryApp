package com.example.groceryapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.databinding.SubCategoryItemBinding
import com.example.groceryapp.model.data.remote.Product
import com.example.groceryapp.model.Constants
import com.squareup.picasso.Picasso

class SubCategoryAdapter (
    private var products : List<Product>,
) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    private lateinit var binding: SubCategoryItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoryAdapter.ViewHolder {
       binding = SubCategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoryAdapter.ViewHolder, position: Int) {
        holder.apply {
            val product = products[position]
            bind(product)
        }

    }

    override fun getItemCount(): Int  = products.size


    //how do you know it is going to be recycler.viewHolder or when you are extending subCategoryAdapter
    inner class ViewHolder(private val binding: SubCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                txtDescription.text = product.description
                txtPrice.text = "\$%.2f".format(product.price)
                txtItemName.text = product.productName
                val url = Constants.BASE_IMAGE_URL + product.image
                try {
                    Picasso.get().load(url).into(binding.imgItem)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
}