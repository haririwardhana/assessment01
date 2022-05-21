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
import org.d3if0044.assessment1_hitungbahanbakar.model.KategoriBB
import org.w3c.dom.Entity

class MainViewModel(private val db: DbDao) : ViewModel() {

    private val hasilHitung = MutableLiveData<HasilHitung?>()

    val data = db.getLastData()

    fun hitungTotal(awal: Float, akhir: Float, jumlah: Float) {
        val jarak = akhir - awal
        val total = (jarak) / jumlah
        val kategori = hasilKategori(total)
        hasilHitung.value = HasilHitung(total, jarak, kategori)
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val dataHasil = Enitity(
                    jarak = jarak,
                    total = total
                )
                db.insert(dataHasil)
            }
        }
    }
    private fun hasilKategori(total: Float): KategoriBB {
        val kategori = when {
            total < 10.0 -> KategoriBB.BOROS
            total >= 20.0  -> KategoriBB.IRIT
            else -> KategoriBB.SEDANG
        }
        return kategori
    }
    fun getHasilHitung(): LiveData<HasilHitung?> = hasilHitung
}