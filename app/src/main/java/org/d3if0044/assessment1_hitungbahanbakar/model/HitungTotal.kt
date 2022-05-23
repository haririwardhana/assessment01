package org.d3if0044.assessment1_hitungbahanbakar.model

import org.d3if0044.assessment1_hitungbahanbakar.database.Enitity

fun Enitity.hitungTotal(): HasilHitung{
    val total = jarak / bensin
    val jarak = jarak
    val kategori = if (jenis){
        when  {
           total < 10.0 -> KategoriBB.BOROS
            total > 27.0 -> KategoriBB.IRIT
            else -> KategoriBB.SEDANG
    }
    }else{
        when  {
            total < 10.0 -> KategoriBB.BOROS
            total > 27.0 -> KategoriBB.IRIT
            else -> KategoriBB.SEDANG
        }
    }
    return HasilHitung(total , jarak , kategori)
}