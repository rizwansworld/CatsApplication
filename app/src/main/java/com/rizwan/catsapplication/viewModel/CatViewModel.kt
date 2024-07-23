package com.rizwan.catsapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizwan.catsapplication.models.CatsState
import com.rizwan.catsapplication.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val repository: CatRepository) : ViewModel() {
    val cats: StateFlow<CatsState>
        get() = repository.cats

    init {
        viewModelScope.launch {
            getCats()
        }
    }

    suspend fun getCats() {
        repository.getCatsList()
    }
}