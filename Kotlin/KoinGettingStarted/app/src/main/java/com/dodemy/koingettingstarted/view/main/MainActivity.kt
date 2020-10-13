package com.dodemy.koingettingstarted.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dodemy.koingettingstarted.view.main.adapter.ImagesAdapter
import com.dodemy.koingettingstarted.R
import com.dodemy.koingettingstarted.data.model.ImageModel
import com.dodemy.koingettingstarted.databinding.ActivityMainBinding

import org.koin.android.viewmodel.ext.android.viewModel



//import com.dodemy.koingettingstarted.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainActivityViewModel by viewModel()
    private val adapter = ImagesAdapter()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        configureViewModel()
        configureViews()

        mainViewModel.getImages()
    }

    private fun configureViewModel() {
        mainViewModel.imageResponseLiveData.observe(
            this,
            Observer { onImagesReceived(it.images) })
    }

    private fun configureViews() {
        binding.lifecycleOwner = this

        binding.imagesRecyclerView.adapter = adapter
    }

    private fun onImagesReceived(imageList: ArrayList<ImageModel>) {
        imageList.let {
            adapter.setData(it)
        }
    }
}
