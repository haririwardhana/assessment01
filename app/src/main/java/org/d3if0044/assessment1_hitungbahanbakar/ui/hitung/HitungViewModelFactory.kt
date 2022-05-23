package org.d3if0044.assessment1_hitungbahanbakar.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0044.assessment1_hitungbahanbakar.database.DbDao

class HitungViewModelFactory (private val db:DbDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HitungViewModel::class.java)){
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}