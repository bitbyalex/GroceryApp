package com.example.groceryapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.databinding.SubCategoryItemBinding
import com.example.groceryapp.model.Constants
import com.example.groceryapp.model.data.local.Product
import com.squareup.picasso.Picasso

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var binding: SubCategoryItemBinding
    private val products = ArrayList<Product>()
    var listener: ProductListListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = SubCategoryItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.apply {
            val product = products[position]
            bind(product)
        }
    }

    override fun getItemCount(): Int {
       return products.size
    }

    inner class ViewHolder(
        private val binding: SubCategoryItemBinding,
        private val listener: ProductListListener?
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(product: Product){
                with(binding){
                    txtDescription.text = product.description
                    txtPrice.text = "\$.2f".format(product.price)
                    txtItemName.text= product.productName
                    val url = Constants.BASE_IMAGE_URL + product.image
                    try {
                        Picasso.get().load(url).into(binding.imgItem)
                    } catch (e : Exception) {
                        e.printStackTrace()
                    }
                    updateQuantity(product, product.quantity.toString())
                    btnMinus.setOnClickListener {
                        updateQuantity(product,edtCount.text.toString(), -1)
                    }
                    btnPlus.setOnClickListener {
                        updateQuantity(product,edtCount.text.toString(),1)
                    }
                    btnAddToCart.setOnClickListener {
                        product.quantity = edtCount.text.toString().toInt()
                        listener?.onAddItemClick(product)
                    }
                }
            }

        private fun updateQuantity(product: Product, quantity: String, add: Int = 0) {
            if(quantity.toInt()+add>-1) {
                product.quantity = quantity.toInt() + add
                binding.edtCount.setText("${product.quantity}")
            }
        }
    }


    interface ProductListListener {
        fun onAddItemClick(product: Product)
    }
}