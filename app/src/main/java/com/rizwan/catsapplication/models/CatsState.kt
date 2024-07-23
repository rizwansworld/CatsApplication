package com.rizwan.catsapplication.models

data class CatsState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cats: List<Cat> = emptyList()
)