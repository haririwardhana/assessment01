package org.d3if0044.assessment1_hitungbahanbakar.ui.listdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0044.assessment1_hitungbahanbakar.database.DbDao
import java.lang.IllegalArgumentException

class listViewModelFactory(private val db:DbDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(listViewModel::class.java)){
            return listViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}