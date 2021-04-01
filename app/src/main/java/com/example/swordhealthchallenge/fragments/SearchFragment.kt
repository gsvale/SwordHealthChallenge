package com.example.swordhealthchallenge.fragments

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.adapters.BreedListAdapter
import com.example.swordhealthchallenge.adapters.BreedsSearchAdapter
import com.example.swordhealthchallenge.databinding.FragmentSearchBinding
import com.example.swordhealthchallenge.models.Breed
import com.example.swordhealthchallenge.network.NetworkClient
import com.example.swordhealthchallenge.viewModels.ListViewModel
import com.example.swordhealthchallenge.viewModels.SearchViewModel
import java.util.ArrayList


class SearchFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }

    private var mBinding: FragmentSearchBinding? = null
    private lateinit var mViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Set DataBinding instance
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        // Set ViewModel
        mViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        // Observers
        mViewModel.isInit().observe(activity as AppCompatActivity, Observer { initViews() })
        mViewModel.getToast().observe(activity as AppCompatActivity, Observer { handleToast(it) })
        mViewModel.getList()
            .observe(activity as AppCompatActivity, Observer { handleList(it) })

        // Binding ViewModel
        mBinding!!.viewModel = mViewModel
        mBinding!!.lifecycleOwner = this

        return mBinding!!.root
    }

    private fun initViews() {

        mBinding!!.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                mViewModel.fetchBreedsByName(
                    NetworkClient.create(context!!),
                    query!!
                )
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        // Set adapter
        val adapter = BreedsSearchAdapter(activity!!, ArrayList())
        mBinding!!.listRv.adapter = adapter

        // Create LinearLayoutManager variable
        val linearLayoutManager = LinearLayoutManager(activity)

        // Set linearLayoutManager var in recyclerview
        mBinding!!.listRv.layoutManager = linearLayoutManager

    }

    private fun handleList(breedList: List<Breed>) {

        val breeds: ArrayList<Breed> = ArrayList()

        if (!breedList.isNullOrEmpty()) {
            breeds.addAll(breedList)
            (mBinding!!.listRv.adapter as BreedsSearchAdapter).addItems(breeds)
        }

    }

    private fun handleToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}