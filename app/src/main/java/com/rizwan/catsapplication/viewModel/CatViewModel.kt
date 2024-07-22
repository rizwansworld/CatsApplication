package com.rizwan.catsapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizwan.catsapplication.models.Cat
import com.rizwan.catsapplication.models.Favorite
import com.rizwan.catsapplication.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val repository: CatRepository) : ViewModel() {
    val cats: StateFlow<List<Cat>>
        get() = repository.cats

    init {
        viewModelScope.launch {
            repository.getCats()
        }
    }
}