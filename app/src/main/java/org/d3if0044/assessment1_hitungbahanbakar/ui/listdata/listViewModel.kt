package org.d3if0044.assessment1_hitungbahanbakar.ui.listdata

import androidx.lifecycle.ViewModel
import org.d3if0044.assessment1_hitungbahanbakar.database.DbDao

class listViewModel(db:DbDao) : ViewModel() {
    val data = db.getLastData()
}