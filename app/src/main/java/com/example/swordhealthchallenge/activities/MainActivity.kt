package com.example.swordhealthchallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.databinding.ActivityMainBinding
import com.example.swordhealthchallenge.fragments.ListFragment
import com.example.swordhealthchallenge.fragments.SearchFragment
import com.example.swordhealthchallenge.network.NetworkClient
import com.example.swordhealthchallenge.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mViewModel: MainViewModel

    private var mSavedInstanceState: Bundle? = null

    private var mListFragment: ListFragment? = null

    private var mSearchFragment: SearchFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSavedInstanceState = savedInstanceState

        // Set DataBinding instance
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set ViewModel
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Observers
        mViewModel.isInit().observe(this, Observer { initViews() })

        // Binding ViewModel
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this

    }

    private fun initViews() {

        if(mSavedInstanceState == null){

            // Set ProductsListFragment instance
            mListFragment = ListFragment.newInstance()

            mSearchFragment = SearchFragment.newInstance()

            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.current_fragment, mListFragment!!).commit()

        }

    }

    fun showListFragment(item: MenuItem) {
        item.isChecked = true
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.current_fragment, mListFragment!!).commit()
    }
    fun showSearchFragment(item: MenuItem) {
        item.isChecked = true
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.current_fragment, mSearchFragment!!).commit()
    }


}