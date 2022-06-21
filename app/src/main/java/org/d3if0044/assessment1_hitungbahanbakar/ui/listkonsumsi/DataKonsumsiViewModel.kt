package org.d3if0044.assessment1_hitungbahanbakar.ui.listkonsumsi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0044.assessment1_hitungbahanbakar.model.DataKonsumsi
import org.d3if0044.assessment1_hitungbahanbakar.network.dataApi

class DataKonsumsiViewModel : ViewModel() {

    private val data = MutableLiveData<List<DataKonsumsi>>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.postValue(dataApi.service.getData())
            } catch (e: Exception){
                Log.d("MainViewModel","Failure: ${e.message}")
            }
        }
    }
    fun getData():LiveData<List<DataKonsumsi>> = data
}