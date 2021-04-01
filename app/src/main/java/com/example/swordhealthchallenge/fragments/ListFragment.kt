package com.example.swordhealthchallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.adapters.BreedListAdapter
import com.example.swordhealthchallenge.databinding.FragmentListBinding
import com.example.swordhealthchallenge.models.Breed
import com.example.swordhealthchallenge.network.NetworkClient
import com.example.swordhealthchallenge.viewModels.ListViewModel
import java.util.ArrayList


class ListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            ListFragment()
    }

    private var mBinding: FragmentListBinding? = null
    private lateinit var mViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Set DataBinding instance
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        // Set ViewModel
        mViewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        // Observers
        mViewModel.isInit().observe(activity as AppCompatActivity, Observer { initViews() })
        mViewModel.getToast().observe(activity as AppCompatActivity, Observer { handleToast(it) })
        mViewModel.getList()
            .observe(activity as AppCompatActivity, Observer { handleList(it) })

        mViewModel.isListMode().observe(activity as AppCompatActivity, Observer { setListMode(it) })
        mViewModel.isGridMode().observe(activity as AppCompatActivity, Observer { setGridMode(it) })

        // Binding ViewModel
        mBinding!!.viewModel = mViewModel
        mBinding!!.lifecycleOwner = this

        return mBinding!!.root
    }

    private fun initViews() {

        mViewModel.fetchBreeds(NetworkClient.create(context!!), "0")

        // Set adapter
        val adapter = BreedListAdapter(activity!!, ArrayList())
        mBinding!!.listRv.adapter = adapter

        // Create LinearLayoutManager variable
        val linearLayoutManager = LinearLayoutManager(activity)

        // Set linearLayoutManager var in recyclerview
        mBinding!!.listRv.layoutManager = linearLayoutManager

        setScrollListener(linearLayoutManager)

    }

    private fun setScrollListener(manager : LinearLayoutManager){

        // Add OnScrollListener to update contents of recyclerview, getting breeds for next pages
        mBinding?.listRv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                val lastCompletelyVisibleItemPosition =
                    manager.findLastCompletelyVisibleItemPosition();

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && lastCompletelyVisibleItemPosition == totalItemCount - 1
                ) {
                    mViewModel.fetchBreeds(
                        NetworkClient.create(context!!),
                        (mViewModel.getLastPage() + 1).toString()
                    )
                }

            }
        })
    }

    private fun handleList(breedList: List<Breed>) {

        val breeds: ArrayList<Breed> = ArrayList()

        if (!breedList.isNullOrEmpty()) {
            breeds.addAll(breedList)
            (mBinding!!.listRv.adapter as BreedListAdapter).addItems(breeds)
        }

    }

    private fun setListMode(isListMode: Boolean) {
        if (isListMode) {
            if (mBinding!!.listRv.layoutManager is GridLayoutManager) {
                val linearLayoutManager = LinearLayoutManager(activity)
                mBinding!!.listRv.layoutManager = linearLayoutManager
                setScrollListener(linearLayoutManager)
            }
        }
    }

    private fun setGridMode(isGridMode: Boolean) {
        if (isGridMode) {
            if (mBinding!!.listRv.layoutManager is LinearLayoutManager) {
                val gridLayoutManager = GridLayoutManager(activity, 2)
                mBinding!!.listRv.layoutManager = gridLayoutManager
                setScrollListener(gridLayoutManager)
            }
        }
    }

    private fun handleToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}