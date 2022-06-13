package org.d3if0044.assessment1_hitungbahanbakar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DbDao {
    @Insert
    fun insert(bahanBakar: Enitity)

    @Query("SELECT * FROM bahanBakar ORDER BY id DESC ")
    fun getLastData(): LiveData<List<Enitity>>

    @Delete
    fun deleteId(bahanBakar: Enitity)

    @Query("DELETE FROM bahanBakar")
    fun clearData()
}