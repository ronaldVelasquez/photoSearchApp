package com.ronald.photosearchapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.VerticalGridPresenter
import androidx.lifecycle.ViewModelProvider
import com.ronald.photosearchapp.R
import com.ronald.photosearchapp.data.FlickrRepository
import com.ronald.photosearchapp.ui.CardPresenter

class MainFragment : VerticalGridSupportFragment(), SearchInterface {
    private lateinit var repository: FlickrRepository
    private lateinit var viewModel: MainViewModel
    private lateinit var updateResults: UpdateResults

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        setUpViews()
    }

    private fun setViewModel() {
        repository = FlickrRepository(getString(R.string.api_key))
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun setUpViews() {
        showTitle(false)
        gridPresenter = VerticalGridPresenter().apply { numberOfColumns = 3 }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listPhoto.observe(viewLifecycleOwner) { listPhoto ->
            val listRowAdapter = ArrayObjectAdapter(CardPresenter())
            listRowAdapter.addAll(0, listPhoto)
            adapter = listRowAdapter
        }
        viewModel.listPhotoBy.observe(viewLifecycleOwner) { listPhoto ->
            updateResults.isGetSomeResult(listPhoto.isNotEmpty())
            val listRowAdapter = ArrayObjectAdapter(CardPresenter())
            listRowAdapter.addAll(0, listPhoto)
            adapter = listRowAdapter
        }
        viewModel.getPhotosRecent()
    }

    override fun searchBy(text: String) {
        viewModel.searchByText(text)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        updateResults = context as UpdateResults
    }
}

interface SearchInterface {
    fun searchBy(text: String);
}

interface UpdateResults {
    fun isGetSomeResult(isResults: Boolean)
}
