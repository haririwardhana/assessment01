package org.d3if0044.assessment1_hitungbahanbakar.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0044.assessment1_hitungbahanbakar.model.HasilHitung
import org.d3if0044.assessment1_hitungbahanbakar.model.KategoriBB

class MainViewModel : ViewModel() {

    private val hasilHitung = MutableLiveData<HasilHitung?>()

    fun hitungTotal(awal: Float, akhir: Float, jumlah: Float) {
        val jarak = akhir - awal
        val total = (jarak) / jumlah
        val kategori = hasilKategori(total)
        hasilHitung.value = HasilHitung(total, jarak, kategori)
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