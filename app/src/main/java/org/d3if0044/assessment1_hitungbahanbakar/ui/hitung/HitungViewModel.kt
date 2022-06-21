package org.d3if0044.assessment1_hitungbahanbakar.ui.hitung

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0044.assessment1_hitungbahanbakar.MainActivity
import org.d3if0044.assessment1_hitungbahanbakar.database.DbDao
import org.d3if0044.assessment1_hitungbahanbakar.database.Enitity
import org.d3if0044.assessment1_hitungbahanbakar.model.DataKonsumsi
import org.d3if0044.assessment1_hitungbahanbakar.model.HasilHitung
import org.d3if0044.assessment1_hitungbahanbakar.model.hitungTotal
import org.d3if0044.assessment1_hitungbahanbakar.network.ApiService
import org.d3if0044.assessment1_hitungbahanbakar.network.UpdateWorker
import org.d3if0044.assessment1_hitungbahanbakar.network.dataApi
import java.util.concurrent.TimeUnit

class HitungViewModel(private val db: DbDao) : ViewModel() {

    private val hasilHitung = MutableLiveData<HasilHitung?>()
//    private val data = MutableLiveData<List<DataKonsumsi>>()

    fun hitungTotal(awal: Float, akhir: Float, jumlah: Float, jenis: Boolean) {
        val dataHasil = Enitity(
            jarak = akhir - awal,
            bensin = jumlah,
            jenis = jenis
        )
        hasilHitung.value = dataHasil.hitungTotal()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }
    fun getHasilHitung(): LiveData<HasilHitung?> = hasilHitung

    fun scheduleUpdater(app: Application){
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES).build()

        WorkManager.getInstance(app).enqueueUniqueWork(MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,request)
    }
//
//    init {
//        retrieveData()
//    }
//
//    private fun retrieveData() {
//        viewModelScope.launch (Dispatchers.IO) {
//            try {
//                data.postValue(dataApi.service.getData())
//            } catch (e: Exception) {
//                Log.d("MainViewModel", "Failure: ${e.message}")
//            }
//        }
//    }

}