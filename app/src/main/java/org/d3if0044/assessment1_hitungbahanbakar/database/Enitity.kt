package org.d3if0044.assessment1_hitungbahanbakar.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bahanBakar")
data class Enitity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jarak: Float,
    var total: Float
)
