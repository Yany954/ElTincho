package com.example.eltincho.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eltincho.domain.Repository
import com.example.eltincho.models.entradas

class EntradaViewModel :ViewModel() {
    val repository= Repository()
    fun fetchEntrada():LiveData<MutableList<entradas>>{
        val mutableData=MutableLiveData<MutableList<entradas>>()
        repository.getEntradasData().observeForever{
            mutableData.value=it
        }
        return  mutableData
    }
}