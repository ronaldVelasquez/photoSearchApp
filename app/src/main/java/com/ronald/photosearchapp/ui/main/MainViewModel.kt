package com.ronald.photosearchapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ronald.photosearchapp.data.FlickrRepository
import androidx.lifecycle.viewModelScope
import com.ronald.photosearchapp.domain.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val repository: FlickrRepository): ViewModel() {
    private var page = 0
    private var maxPage = 0
    private val photosPerPage = 100
    private val _listPhoto : MutableLiveData<List<Photo>> = MutableLiveData<List<Photo>>()
    val listPhoto : LiveData<List<Photo>> = _listPhoto
    private val _listPhotoBy : MutableLiveData<List<Photo>> = MutableLiveData<List<Photo>>()
    val listPhotoBy : LiveData<List<Photo>> = _listPhotoBy

    fun getPhotosRecent() {
        viewModelScope.launch(Dispatchers.IO) {
            val listPhoto = repository.getFeed(1, 100)
            withContext(Dispatchers.Main) {
                _listPhoto.value = listPhoto
            }
        }
    }

    fun searchByText(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
           val listPhoto = repository.searchPhotosBy(1, 100, text)
            withContext(Dispatchers.Main) {
                _listPhotoBy.value = listPhoto
            }
        }
    }
}

class MainViewModelFactory(val repository: FlickrRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FlickrRepository::class.java).newInstance(repository)
    }
}