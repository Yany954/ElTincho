package com.example.eltincho.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eltincho.domain.repository
import com.example.eltincho.models.favoritos

class FavoritosViewModel:ViewModel() {
    val repository= repository()
    fun fetchFavoritosData(): LiveData<MutableList<favoritos>> {
        val mutableData= MutableLiveData<MutableList<favoritos>>()
        repository.getDataFavoritosData().observeForever{
            mutableData.value=it
        }
        return mutableData
    }
}