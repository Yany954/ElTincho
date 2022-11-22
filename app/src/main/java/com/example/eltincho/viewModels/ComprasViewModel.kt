package com.example.eltincho.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eltincho.domain.repository
import com.example.eltincho.models.compras

class ComprasViewModel :ViewModel() {
    val repository= repository()
    fun fetchComprasData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        repository.getDataComprasData().observeForever{
            mutableData.value=it
        }
        return mutableData
    }
}