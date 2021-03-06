package com.shkiper.foodapp.ui.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shkiper.foodapp.R
import com.shkiper.foodapp.databinding.ActivityMainBinding
import com.shkiper.foodapp.extensions.createFactory
import com.shkiper.foodapp.repo.OrderRepoI
import com.shkiper.foodapp.room.entity.Food
import com.shkiper.foodapp.ui.MainViewModel
import com.shkiper.foodapp.ui.adapter.FoodAdapter
import com.shkiper.foodapp.ui.adapter.OnClickListener
import com.shkiper.foodapp.ui.cart.CartActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: FoodAdapter

    private lateinit var binding: ActivityMainBinding

    private var foodList: List<Food> = listOf()

    private var itemPosition: Int = 0

    @Inject
    lateinit var orderRepoI: OrderRepoI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
        getAllList()
        setObserver()
        setRecyclerView()
        onClick()
    }

    private fun init() {
        Log.d(TAG, " >>> Initializing viewModel")

        val factory = MainViewModel(orderRepoI).createFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun getAllList() {
        viewModel.getOrderList()
    }

    private fun setObserver() {
        viewModel.observableState.observe(this, Observer {
            when {
                it.success -> {
                    Handler().postDelayed({
                        binding.state = it
                    }, 700)

                    adapter.result = it.list!!
                    adapter.notifyItemChanged(itemPosition)
                    calculateTotalItem(it.list!!)
                }
                else -> binding.state = it
            }
        })
    }

    private fun calculateTotalItem(list: List<Food>) {
        var totalItem = 0
        list.forEach {
            totalItem += it.quantity!!
        }
        binding.totalItem = totalItem.toString()
    }

    private fun setRecyclerView() {
        rv_food.layoutManager = LinearLayoutManager(this)
        adapter = FoodAdapter(foodList, object : OnClickListener {
            override fun update(food: Food, position: Int) {
                itemPosition = position
                viewModel.updateItem(food)
            }
        })
        rv_food.adapter = adapter
        rv_food.itemAnimator = null
    }

    private fun onClick() {
        view_bottom.setOnClickListener {
            CartActivity.start(this)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}