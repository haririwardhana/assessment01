package org.d3if0044.assessment1_hitungbahanbakar.ui.listkonsumsi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0044.assessment1_hitungbahanbakar.model.DataKonsumsi
import org.d3if0044.assessment1_hitungbahanbakar.network.ApiStatus
import org.d3if0044.assessment1_hitungbahanbakar.network.dataApi

class DataKonsumsiViewModel : ViewModel() {

    private val data = MutableLiveData<List<DataKonsumsi>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(dataApi.service.getData())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception){
                Log.d("MainViewModel","Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getData():LiveData<List<DataKonsumsi>> = data

    fun getStatus(): LiveData<ApiStatus> = status
}