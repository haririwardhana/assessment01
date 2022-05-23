package org.d3if0044.assessment1_hitungbahanbakar.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0044.assessment1_hitungbahanbakar.database.DbDao
import org.d3if0044.assessment1_hitungbahanbakar.database.Enitity
import org.d3if0044.assessment1_hitungbahanbakar.model.HasilHitung
import org.d3if0044.assessment1_hitungbahanbakar.model.hitungTotal

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
}