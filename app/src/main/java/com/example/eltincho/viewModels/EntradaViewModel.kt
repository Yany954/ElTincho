package com.example.eltincho.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eltincho.domain.repository
import com.example.eltincho.models.entradas

class EntradaViewModel :ViewModel() {
    val repository= repository()
    fun fetchEntrada():LiveData<MutableList<entradas>>{
        val mutableData=MutableLiveData<MutableList<entradas>>()
        repository.getEntradasData().observeForever{
            mutableData.value=it
        }
        return  mutableData
    }
}