package com.example.swordhealthchallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.databinding.ActivityDetailsBinding
import com.example.swordhealthchallenge.models.Breed
import com.example.swordhealthchallenge.viewModels.DetailsViewModel
import com.example.swordhealthchallenge.viewModels.DetailsViewModelFactory
import com.example.swordhealthchallenge.viewModels.MainViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDetailsBinding

    private lateinit var mViewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set DataBinding instance
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        // Get Intent Bundle
        val bundle = intent.extras
        var breed : Breed? = null

        if(bundle != null){
            breed = bundle.getSerializable(Breed::class.java.toString()) as Breed
        }

        // Set ViewModel
        mViewModel = ViewModelProvider(this, DetailsViewModelFactory(breed!!)).get(DetailsViewModel::class.java)

        // Binding ViewModel
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this

    }


}