package com.example.demo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.ui.adapters.ImageCarouselAdapter
import com.example.demo.ui.adapters.VerticalListAdapter
import com.example.demo.utils.CommonUtils
import com.example.demo.viewmodel.MainViewModelImpl
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * this is the main activity of the application
 * */
class MainActivity : AppCompatActivity() {

    private lateinit var imageCarouselAdapter: ImageCarouselAdapter
    private lateinit var verticalListAdapter: VerticalListAdapter
    private lateinit var viewModel: MainViewModelImpl
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModelImpl::class.java]

        verticalListAdapter = VerticalListAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = verticalListAdapter
        }

        //to filter out data from the whole list
        binding.searchEditText.apply {
            setOnClickListener {
                isIconified = false
                requestFocus()
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.filterListData(newText.toString().trim())
                    return true
                }
            })
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setSelection(position)
                binding.searchEditText.apply{
                    setQuery("", false)
                    clearFocus()
                }
            }
        })

        binding.fab.setOnClickListener {
            val size = viewModel.filteredListData.value.size
            val top3Chars = viewModel.getStatistics()
            CommonUtils.showBottomSheetDialog(this@MainActivity, size, top3Chars)
        }

        setObservers()
        viewModel.getCarousalData()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            viewModel.carousalList.collectLatest { carousalList ->
                imageCarouselAdapter = ImageCarouselAdapter(carousalList)
                binding.viewPager.adapter = imageCarouselAdapter
                TabLayoutMediator(binding.dotsIndicator, binding.viewPager) { _, _ -> }.attach()
            }
        }

        lifecycleScope.launch {
            viewModel.filteredListData.collectLatest { filteredItems ->
                verticalListAdapter.updateData(filteredItems)
            }
        }
    }
}