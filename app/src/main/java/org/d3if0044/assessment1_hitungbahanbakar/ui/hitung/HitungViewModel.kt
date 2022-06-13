package org.d3if0044.assessment1_hitungbahanbakar.ui.hitung

import android.app.Application
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
import org.d3if0044.assessment1_hitungbahanbakar.model.HasilHitung
import org.d3if0044.assessment1_hitungbahanbakar.model.hitungTotal
import org.d3if0044.assessment1_hitungbahanbakar.network.UpdateWorker
import java.util.concurrent.TimeUnit

class HitungViewModel(private val db: DbDao) : ViewModel() {

    private val hasilHitung = MutableLiveData<HasilHitung?>()

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

}