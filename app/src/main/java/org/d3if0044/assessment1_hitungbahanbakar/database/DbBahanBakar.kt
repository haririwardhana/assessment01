package org.d3if0044.assessment1_hitungbahanbakar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Enitity::class], version = 2, exportSchema = false)
abstract class DbBahanBakar : RoomDatabase() {
    abstract val dao: DbDao

    companion object{
        @Volatile
        private var INSTANCE: DbBahanBakar? = null

        fun getInstance(context: Context): DbBahanBakar {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,DbBahanBakar::class.java,
                        "bahanBakar.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}