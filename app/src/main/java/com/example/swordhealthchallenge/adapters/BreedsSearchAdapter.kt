package com.example.swordhealthchallenge.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.activities.DetailsActivity
import com.example.swordhealthchallenge.databinding.AdapterItemBreedListBinding
import com.example.swordhealthchallenge.databinding.AdapterItemBreedSearchBinding
import com.example.swordhealthchallenge.models.Breed
import com.example.swordhealthchallenge.viewModels.ItemBreedViewModel
import java.util.ArrayList

class BreedsSearchAdapter (
    private val context: Context,
    private val list: ArrayList<Breed>
) : RecyclerView.Adapter<BreedsSearchAdapter.BreedAdapterViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    // Create View Holders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsSearchAdapter.BreedAdapterViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        // Set DataBinding instance
        val binding = DataBindingUtil.inflate<AdapterItemBreedSearchBinding>(
            layoutInflater!!,
            R.layout.adapter_item_breed_search,
            parent,
            false
        )

        return BreedsSearchAdapter.BreedAdapterViewHolder(context, binding)
    }

    // OnBind method
    override fun onBindViewHolder(holder: BreedsSearchAdapter.BreedAdapterViewHolder, position: Int) {
        holder.bindBreed(list[position])
    }

    // Get number/count of items of adapter
    override fun getItemCount(): Int {
        return list.size
    }

    // Method to add items
    fun addItems(values: ArrayList<Breed>?) {
        if (!values.isNullOrEmpty()) {
            list.addAll(values)
        }
        notifyDataSetChanged()
    }

    fun clearItems() {
        list.clear()
        notifyDataSetChanged()
    }

    class BreedAdapterViewHolder(
        private val context: Context,
        private val binding: AdapterItemBreedSearchBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBreed(breed: Breed) {

            // Set and Binding ViewModel
            val viewModel = ItemBreedViewModel(breed)

            // Observer
            viewModel.getGoToDetails().observe(context as AppCompatActivity, Observer {
                if(it){
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra(Breed::class.java.toString(), breed)
                    context.startActivity(intent)
                }
            })

            binding.viewModel = viewModel
            binding.lifecycleOwner = context as AppCompatActivity
        }


    }

}