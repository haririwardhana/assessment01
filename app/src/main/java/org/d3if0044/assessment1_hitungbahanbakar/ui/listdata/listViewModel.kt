package org.d3if0044.assessment1_hitungbahanbakar.ui.listdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0044.assessment1_hitungbahanbakar.database.DbDao
import org.d3if0044.assessment1_hitungbahanbakar.database.Enitity

class listViewModel(private val db:DbDao) : ViewModel() {
    val data = db.getLastData()

    fun hapusSemua() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.clearData()
        }
    }
}